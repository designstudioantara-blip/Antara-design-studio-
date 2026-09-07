package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val title: String,
  val clientName: String,
  val clientPhone: String,
  val location: String,
  val spaceType: String,
  val totalAreaSqFt: Int,
  val budgetLakhs: Double,
  val progressPercent: Int,
  val currentPhase: String,
  val startDate: String,
  val targetHandoverDate: String,
  val leadDesigner: String,
  val projectManager: String,
  val status: String, // "In Progress", "Design Review", "Completed", "Upcoming"
  val coverImageUrl: String,
  val unreadUpdatesCount: Int = 0
)

@Entity(tableName = "portfolio_items")
data class PortfolioItemEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val title: String,
  val category: String, // "Residential", "Commercial", "Penthouse", "Villa", "Hospitality"
  val location: String,
  val areaSqFt: Int,
  val style: String,
  val tagline: String,
  val description: String,
  val beforeImageDesc: String,
  val afterImageDesc: String,
  val keyFeaturesJson: String, // Comma separated or JSON string
  val isFeatured: Boolean = false,
  val completionYear: String = "2024"
)

@Entity(tableName = "consultations")
data class ConsultationEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val clientName: String,
  val clientPhone: String,
  val clientEmail: String,
  val preferredDate: String,
  val preferredTimeSlot: String,
  val consultationType: String,
  val spaceType: String,
  val approximateSqFt: Int,
  val budgetRange: String,
  val siteAddress: String,
  val specialRequirements: String,
  val status: String = "Confirmed", // "Confirmed", "Completed", "Follow-up"
  val createdTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "material_samples")
data class MaterialSampleEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val name: String,
  val category: String, // "Marble & Stone", "Veneer & Millwork", etc.
  val brandOrigin: String,
  val specification: String,
  val costTier: String, // "$$$", "$$$$", "$$$$$"
  val status: String, // "Approved by Client", "Under Review", "Alternate Proposed"
  val roomZone: String, // "Living Salon", "Master Suite", "Gourmet Kitchen"
  val isClientApproved: Boolean = false,
  val sampleRequested: Boolean = false
)

@Entity(tableName = "chat_messages")
data class ChatMessageEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val projectId: Long = 1,
  val senderName: String,
  val senderRole: String, // "Client", "Designer", "Project Manager", "Admin"
  val messageText: String,
  val timestamp: String,
  val isActionableCard: Boolean = false,
  val actionTitle: String = "",
  val isCompletedAction: Boolean = false,
  val isFromClient: Boolean = false
)

@Entity(tableName = "invoices")
data class InvoiceEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val invoiceNumber: String,
  val milestoneTitle: String,
  val milestonePercentage: Int,
  val amountInRupees: Long,
  val dueDate: String,
  val status: String, // "Paid", "Pending Approval", "Upcoming"
  val paidDate: String = "",
  val transactionRef: String = ""
)
