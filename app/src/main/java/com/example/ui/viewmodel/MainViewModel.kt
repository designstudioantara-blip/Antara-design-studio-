package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.local.ChatMessageEntity
import com.example.data.local.ConsultationEntity
import com.example.data.local.InvoiceEntity
import com.example.data.local.MaterialSampleEntity
import com.example.data.local.PortfolioItemEntity
import com.example.data.local.ProjectEntity
import com.example.data.model.ConsultationType
import com.example.data.model.DesignStyle
import com.example.data.model.FinishTier
import com.example.data.model.SpaceType
import com.example.data.model.UserRole
import com.example.data.repository.AppRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel(private val repository: AppRepository) : ViewModel() {

  // Current Role
  private val _currentRole = MutableStateFlow(UserRole.CLIENT)
  val currentRole: StateFlow<UserRole> = _currentRole.asStateFlow()

  fun setRole(role: UserRole) {
    _currentRole.value = role
  }

  // Active Project ID for client view
  private val _activeProjectId = MutableStateFlow(1L)
  val activeProjectId: StateFlow<Long> = _activeProjectId.asStateFlow()

  // Projects list
  val projects: StateFlow<List<ProjectEntity>> = repository.allProjects
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  // Portfolio with Category filter
  private val _selectedPortfolioCategory = MutableStateFlow("All")
  val selectedPortfolioCategory: StateFlow<String> = _selectedPortfolioCategory.asStateFlow()

  val portfolioList: StateFlow<List<PortfolioItemEntity>> = combine(
    repository.allPortfolio,
    _selectedPortfolioCategory
  ) { items, cat ->
    if (cat == "All") items else items.filter { it.category.equals(cat, ignoreCase = true) }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  fun setPortfolioCategory(category: String) {
    _selectedPortfolioCategory.value = category
  }

  // Consultations
  val consultations: StateFlow<List<ConsultationEntity>> = repository.allConsultations
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  private val _bookingSuccessMessage = MutableStateFlow<String?>(null)
  val bookingSuccessMessage: StateFlow<String?> = _bookingSuccessMessage.asStateFlow()

  fun clearBookingMessage() {
    _bookingSuccessMessage.value = null
  }

  fun bookConsultation(
    name: String,
    phone: String,
    email: String,
    date: String,
    timeSlot: String,
    type: ConsultationType,
    spaceType: String,
    sqFt: Int,
    budgetRange: String,
    address: String,
    requirements: String
  ) {
    viewModelScope.launch {
      val entity = ConsultationEntity(
        clientName = name,
        clientPhone = phone,
        clientEmail = email,
        preferredDate = date,
        preferredTimeSlot = timeSlot,
        consultationType = type.title,
        spaceType = spaceType,
        approximateSqFt = sqFt,
        budgetRange = budgetRange,
        siteAddress = address,
        specialRequirements = requirements,
        status = "Confirmed"
      )
      repository.bookConsultation(entity)
      _bookingSuccessMessage.value = "Consultation successfully scheduled with Antara Design Studio for $date ($timeSlot). Our lead architect will connect with you."
    }
  }

  fun updateConsultationStatus(id: Long, status: String) {
    viewModelScope.launch {
      repository.updateConsultationStatus(id, status)
    }
  }

  // Material Samples
  val materials: StateFlow<List<MaterialSampleEntity>> = repository.allMaterials
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  fun toggleMaterialApproval(id: Long, currentApproved: Boolean) {
    viewModelScope.launch {
      val newStatus = if (!currentApproved) "Approved by Client" else "Under Review"
      repository.updateMaterialApproval(id, !currentApproved, newStatus)
    }
  }

  fun toggleMaterialSampleRequest(id: Long, currentRequested: Boolean) {
    viewModelScope.launch {
      repository.toggleSampleRequest(id, !currentRequested)
    }
  }

  // Chat Messages
  val chatMessages: StateFlow<List<ChatMessageEntity>> = repository.allChatMessages
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  fun sendChatMessage(text: String, isClient: Boolean = true) {
    if (text.isBlank()) return
    viewModelScope.launch {
      val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
      val timestamp = "Today, " + timeFormat.format(Date())
      val roleName = when (_currentRole.value) {
        UserRole.CLIENT -> "VIP Client"
        UserRole.DESIGNER -> "Ar. Hardik Patel (Lead Designer)"
        UserRole.PROJECT_MANAGER -> "Er. Sunny Shah (Project Manager)"
        UserRole.ADMIN -> "Studio Director"
      }
      val entity = ChatMessageEntity(
        projectId = _activeProjectId.value,
        senderName = if (isClient) "Mr. Rajesh Mehta" else roleName,
        senderRole = _currentRole.value.label,
        messageText = text,
        timestamp = timestamp,
        isActionableCard = false,
        isFromClient = isClient
      )
      repository.sendChatMessage(entity)
    }
  }

  fun completeChatAction(messageId: Long) {
    viewModelScope.launch {
      repository.markChatActionCompleted(messageId)
    }
  }

  // Invoices & Payments
  val invoices: StateFlow<List<InvoiceEntity>> = repository.allInvoices
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  private val _paymentSuccessMessage = MutableStateFlow<String?>(null)
  val paymentSuccessMessage: StateFlow<String?> = _paymentSuccessMessage.asStateFlow()

  fun clearPaymentMessage() {
    _paymentSuccessMessage.value = null
  }

  fun payInvoice(invoiceId: Long, amount: Long, milestone: String) {
    viewModelScope.launch {
      val timeFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
      val paidDate = timeFormat.format(Date())
      val txnRef = "ADS" + System.currentTimeMillis().toString().takeLast(8)
      repository.payInvoice(invoiceId, paidDate, txnRef)
      _paymentSuccessMessage.value = "Payment of ₹${String.format("%,d", amount)} for '$milestone' verified! Digital Receipt Generated (Ref: $txnRef)."
    }
  }

  // Interactive Cost Estimator State
  private val _estimatorSpaceType = MutableStateFlow(SpaceType.RESIDENTIAL_3BHK)
  val estimatorSpaceType: StateFlow<SpaceType> = _estimatorSpaceType.asStateFlow()

  private val _estimatorSqFt = MutableStateFlow(2400)
  val estimatorSqFt: StateFlow<Int> = _estimatorSqFt.asStateFlow()

  private val _estimatorTier = MutableStateFlow(FinishTier.PREMIUM_LUXURY)
  val estimatorTier: StateFlow<FinishTier> = _estimatorTier.asStateFlow()

  private val _selectedRooms = MutableStateFlow(
    setOf("Living & Dining Salon", "Master Bedroom Suite", "Modular Gourmet Kitchen", "Designer False Ceilings", "Smart Automation")
  )
  val selectedRooms: StateFlow<Set<String>> = _selectedRooms.asStateFlow()

  fun setEstimatorSpaceType(type: SpaceType) {
    _estimatorSpaceType.value = type
  }

  fun setEstimatorSqFt(sqFt: Int) {
    _estimatorSqFt.value = sqFt
  }

  fun setEstimatorTier(tier: FinishTier) {
    _estimatorTier.value = tier
  }

  fun toggleEstimatorRoom(room: String) {
    val current = _selectedRooms.value.toMutableSet()
    if (current.contains(room)) {
      if (current.size > 1) current.remove(room)
    } else {
      current.add(room)
    }
    _selectedRooms.value = current
  }

  // Calculated Estimate
  val calculatedEstimateInLakhs: StateFlow<Double> = combine(
    _estimatorSpaceType,
    _estimatorSqFt,
    _estimatorTier,
    _selectedRooms
  ) { space, sqFt, tier, rooms ->
    val baseRate = space.baseRatePerSqFt
    val tierMultiplier = tier.multiplier
    val roomMultiplier = 0.6f + (rooms.size * 0.08f).coerceAtMost(0.4f)
    val totalCost = sqFt * baseRate * tierMultiplier * roomMultiplier
    totalCost / 100000.0
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 45.0)

  // Style Quiz State
  private val _quizAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
  val quizAnswers: StateFlow<Map<Int, Int>> = _quizAnswers.asStateFlow()

  private val _matchedStyle = MutableStateFlow<DesignStyle?>(null)
  val matchedStyle: StateFlow<DesignStyle?> = _matchedStyle.asStateFlow()

  fun answerQuizQuestion(questionIndex: Int, optionIndex: Int) {
    val map = _quizAnswers.value.toMutableMap()
    map[questionIndex] = optionIndex
    _quizAnswers.value = map

    if (map.size >= 4) {
      val styles = DesignStyle.values()
      val selectedIndex = (map.values.sum()) % styles.size
      _matchedStyle.value = styles[selectedIndex]
    }
  }

  fun resetQuiz() {
    _quizAnswers.value = emptyMap()
    _matchedStyle.value = null
  }

  // AI Design Advisor & Spatial Concierge
  private val _aiQuery = MutableStateFlow("Recommended marble for low-light living salons?")
  val aiQuery: StateFlow<String> = _aiQuery.asStateFlow()

  private val _aiResponse = MutableStateFlow<String?>(
    "For spaces with limited natural daylight, we recommend Italian Statuario White or Calacatta Gold with polished mirror finish and warm 2700K indirect cove lighting. The high light-reflectance value (LRV) amplifies ambient brightness while adding natural gold veining."
  )
  val aiResponse: StateFlow<String?> = _aiResponse.asStateFlow()

  private val _isAiGenerating = MutableStateFlow(false)
  val isAiGenerating: StateFlow<Boolean> = _isAiGenerating.asStateFlow()

  fun askAiAdvisor(prompt: String) {
    if (prompt.isBlank()) return
    _aiQuery.value = prompt
    viewModelScope.launch {
      _isAiGenerating.value = true
      delay(1000)
      val response = when {
        prompt.contains("kitchen", ignoreCase = true) ->
          "For luxury turnkey kitchens, Antara recommends Quartz/Granite seamless countertops with Blum motorized servo-drive cabinets, fluted tinted glass vitrines with 3000K profile lighting, and concealed Faber island chimneys with anti-grease filtration."
        prompt.contains("lighting", ignoreCase = true) || prompt.contains("lux", ignoreCase = true) ->
          "Antara's lighting architecture uses 3 tiers: 1) Architectural magnetic downlights (2700K Warm, 150-200 lux for ambient mood), 2) Concealed indirect cove LED ribbons (CRI 95+), and 3) Sculptural accent pendants in brushed champagne gold over dining & islands."
        prompt.contains("veneer", ignoreCase = true) || prompt.contains("humidity", ignoreCase = true) || prompt.contains("coastal", ignoreCase = true) ->
          "In Surat's coastal humidity, natural veneers must receive high-solid Italian polyurethane (PU) sealer with open-pore ultra-matte finish (5% sheen). Use Marine-grade Gurjan IS:710 plywood substrate to eliminate moisture warping."
        prompt.contains("courtyard", ignoreCase = true) || prompt.contains("biophilic", ignoreCase = true) ->
          "For indoor courtyards in Gujarat: combine natural rough-cut Silver Travertine cladding, automated low-voltage drip misting for Ficus and Philodendrons, and double-height skylight baffles to moderate summer thermal gain."
        else ->
          "Based on Antara's signature design principles: Integrate natural textures (dyed oak, travertine stone), recessed amber ambient coves, custom bronze metallic inlays, and uncluttered architectural symmetry to achieve lasting luxury."
      }
      _aiResponse.value = response
      _isAiGenerating.value = false
    }
  }
}

class MainViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return MainViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}
