package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Weekend
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.local.AppDatabase
import com.example.data.model.UserRole
import com.example.data.repository.AppRepository
import com.example.ui.components.AntaraTopBar
import com.example.ui.screens.admin.AdminDashboardScreen
import com.example.ui.screens.admin.AiDesignAdvisorScreen
import com.example.ui.screens.client.ChatScreen
import com.example.ui.screens.client.ClientDashboardScreen
import com.example.ui.screens.client.DocumentsScreen
import com.example.ui.screens.client.InvoicesScreen
import com.example.ui.screens.client.MaterialStudioScreen
import com.example.ui.screens.client.ProjectTimelineScreen
import com.example.ui.screens.public.BookingScreen
import com.example.ui.screens.public.ContactScreen
import com.example.ui.screens.public.CostEstimatorScreen
import com.example.ui.screens.public.HomeScreen
import com.example.ui.screens.public.PortfolioScreen
import com.example.ui.screens.public.ServicesScreen
import com.example.ui.screens.public.StyleQuizScreen
import com.example.ui.theme.CharcoalBorder
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalElevated
import com.example.ui.theme.CharcoalSurface
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.PureWhite
import com.example.ui.viewmodel.MainViewModel

enum class AppRoleMode {
  PUBLIC_STUDIO,
  CLIENT_PORTAL,
  ADMIN_AI
}

enum class PublicTab(val title: String, val icon: ImageVector) {
  HOME("Home", Icons.Default.Home),
  PORTFOLIO("Works", Icons.Default.Architecture),
  SERVICES("Services", Icons.Default.Weekend),
  ESTIMATOR("Estimator", Icons.Default.Calculate),
  STYLE_QUIZ("Style Quiz", Icons.Default.Psychology),
  BOOKING("Book", Icons.Default.CalendarMonth),
  CONTACT("Studio", Icons.Default.ContactPage)
}

enum class ClientTab(val title: String, val icon: ImageVector) {
  DASHBOARD("Project", Icons.Default.Dashboard),
  TIMELINE("Timeline", Icons.Default.Timeline),
  MATERIALS("Materials", Icons.Default.Layers),
  INVOICES("Invoices", Icons.Default.CreditCard),
  CHAT("Studio Chat", Icons.Default.Chat),
  DOCS("Blueprints", Icons.Default.Folder)
}

enum class AdminTab(val title: String, val icon: ImageVector) {
  CRM_LEADS("CRM & Leads", Icons.Default.AdminPanelSettings),
  AI_ADVISOR("Spatial AI", Icons.Default.AutoAwesome)
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      MyApplicationTheme {
        val context = LocalContext.current
        val db = remember { AppDatabase.getDatabase(context) }
        val repository = remember { AppRepository(db.appDao()) }
        val mainViewModel: MainViewModel = viewModel(
          factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
              return MainViewModel(repository) as T
            }
          }
        )

        AntaraApp(viewModel = mainViewModel)
      }
    }
  }
}

@Composable
fun AntaraApp(viewModel: MainViewModel) {
  val userRole by viewModel.currentRole.collectAsStateWithLifecycle()
  var currentRoleMode by remember { mutableStateOf(AppRoleMode.PUBLIC_STUDIO) }
  var publicTab by remember { mutableStateOf(PublicTab.HOME) }
  var clientTab by remember { mutableStateOf(ClientTab.DASHBOARD) }
  var adminTab by remember { mutableStateOf(AdminTab.CRM_LEADS) }

  Scaffold(
    containerColor = CharcoalDark,
    topBar = {
      Column(modifier = Modifier.background(CharcoalSurface)) {
        AntaraTopBar(
          currentRole = userRole,
          onRoleChanged = { newRole ->
            viewModel.setRole(newRole)
            currentRoleMode = when (newRole) {
              UserRole.CLIENT -> AppRoleMode.CLIENT_PORTAL
              UserRole.DESIGNER, UserRole.PROJECT_MANAGER, UserRole.ADMIN -> AppRoleMode.ADMIN_AI
            }
          },
          onContactClick = {
            currentRoleMode = AppRoleMode.PUBLIC_STUDIO
            publicTab = PublicTab.CONTACT
          }
        )

        // Luxury Role Switcher Banner
        RoleSwitcherBar(
          currentRole = currentRoleMode,
          onSelectRole = { newMode ->
            currentRoleMode = newMode
            val mappedRole = when (newMode) {
              AppRoleMode.PUBLIC_STUDIO -> UserRole.CLIENT
              AppRoleMode.CLIENT_PORTAL -> UserRole.CLIENT
              AppRoleMode.ADMIN_AI -> UserRole.ADMIN
            }
            viewModel.setRole(mappedRole)
          }
        )
      }
    },
    bottomBar = {
      AntaraBottomNavBar(
        currentRole = currentRoleMode,
        publicTab = publicTab,
        onSelectPublicTab = { publicTab = it },
        clientTab = clientTab,
        onSelectClientTab = { clientTab = it },
        adminTab = adminTab,
        onSelectAdminTab = { adminTab = it }
      )
    },
    contentWindowInsets = WindowInsets(0.dp)
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(CharcoalDark)
    ) {
      Crossfade(
        targetState = Triple(currentRoleMode, publicTab, clientTab),
        label = "screen_crossfade"
      ) { stateTriple ->
        val role = stateTriple.first
        val pTab = stateTriple.second
        val cTab = stateTriple.third
        when (role) {
          AppRoleMode.PUBLIC_STUDIO -> {
            when (pTab) {
              PublicTab.HOME -> HomeScreen(
                viewModel = viewModel,
                onNavigateToBooking = { publicTab = PublicTab.BOOKING },
                onNavigateToPortfolio = { publicTab = PublicTab.PORTFOLIO },
                onNavigateToEstimator = { publicTab = PublicTab.ESTIMATOR },
                onNavigateToStyleQuiz = { publicTab = PublicTab.STYLE_QUIZ },
                onNavigateToServices = { publicTab = PublicTab.SERVICES },
                onNavigateToContact = { publicTab = PublicTab.CONTACT }
              )
              PublicTab.PORTFOLIO -> PortfolioScreen(
                viewModel = viewModel,
                onBookConsultation = { publicTab = PublicTab.BOOKING }
              )
              PublicTab.SERVICES -> ServicesScreen(
                onBookConsultation = { publicTab = PublicTab.BOOKING },
                onCalculateEstimate = { publicTab = PublicTab.ESTIMATOR }
              )
              PublicTab.ESTIMATOR -> CostEstimatorScreen(
                viewModel = viewModel,
                onProceedToBooking = { publicTab = PublicTab.BOOKING }
              )
              PublicTab.STYLE_QUIZ -> StyleQuizScreen(
                viewModel = viewModel,
                onBookMatchedConsultation = { publicTab = PublicTab.BOOKING }
              )
              PublicTab.BOOKING -> BookingScreen(
                viewModel = viewModel,
                onNavigateToClientPortal = { currentRoleMode = AppRoleMode.CLIENT_PORTAL }
              )
              PublicTab.CONTACT -> ContactScreen(
                onBookAppointment = { publicTab = PublicTab.BOOKING }
              )
            }
          }
          AppRoleMode.CLIENT_PORTAL -> {
            when (cTab) {
              ClientTab.DASHBOARD -> ClientDashboardScreen(
                viewModel = viewModel,
                onNavigateToTimeline = { clientTab = ClientTab.TIMELINE },
                onNavigateToMaterials = { clientTab = ClientTab.MATERIALS },
                onNavigateToInvoices = { clientTab = ClientTab.INVOICES },
                onNavigateToChat = { clientTab = ClientTab.CHAT },
                onNavigateToDocs = { clientTab = ClientTab.DOCS }
              )
              ClientTab.TIMELINE -> ProjectTimelineScreen()
              ClientTab.MATERIALS -> MaterialStudioScreen(viewModel = viewModel)
              ClientTab.INVOICES -> InvoicesScreen(viewModel = viewModel)
              ClientTab.CHAT -> ChatScreen(
                viewModel = viewModel,
                onNavigateToMaterials = { clientTab = ClientTab.MATERIALS }
              )
              ClientTab.DOCS -> DocumentsScreen()
            }
          }
          AppRoleMode.ADMIN_AI -> {
            when (adminTab) {
              AdminTab.CRM_LEADS -> AdminDashboardScreen(viewModel = viewModel)
              AdminTab.AI_ADVISOR -> AiDesignAdvisorScreen(viewModel = viewModel)
            }
          }
        }
      }
    }
  }
}

@Composable
private fun RoleSwitcherBar(
  currentRole: AppRoleMode,
  onSelectRole: (AppRoleMode) -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(CharcoalSurface)
      .padding(horizontal = 12.dp, vertical = 6.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    RoleTabItem(
      title = "Public Studio",
      icon = Icons.Default.Public,
      isSelected = currentRole == AppRoleMode.PUBLIC_STUDIO,
      onClick = { onSelectRole(AppRoleMode.PUBLIC_STUDIO) },
      modifier = Modifier.weight(1f)
    )
    RoleTabItem(
      title = "Client Portal",
      icon = Icons.Default.Person,
      isSelected = currentRole == AppRoleMode.CLIENT_PORTAL,
      onClick = { onSelectRole(AppRoleMode.CLIENT_PORTAL) },
      modifier = Modifier.weight(1f)
    )
    RoleTabItem(
      title = "Admin & AI",
      icon = Icons.Default.AdminPanelSettings,
      isSelected = currentRole == AppRoleMode.ADMIN_AI,
      onClick = { onSelectRole(AppRoleMode.ADMIN_AI) },
      modifier = Modifier.weight(1f)
    )
  }
}

@Composable
private fun RoleTabItem(
  title: String,
  icon: ImageVector,
  isSelected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    shape = RoundedCornerShape(6.dp),
    color = if (isSelected) GoldPrimary else CharcoalElevated,
    border = BorderStroke(0.6.dp, if (isSelected) GoldLight else CharcoalBorder),
    modifier = modifier
      .clickable { onClick() }
      .testTag("role_tab_${title.lowercase().replace(" ", "_").replace("&", "and")}")
  ) {
    Row(
      modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = if (isSelected) CharcoalDark else GoldLight,
        modifier = Modifier.size(13.dp)
      )
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = title,
        fontFamily = FontFamily.SansSerif,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
        fontSize = 10.5.sp,
        color = if (isSelected) CharcoalDark else PureWhite
      )
    }
  }
}

@Composable
private fun AntaraBottomNavBar(
  currentRole: AppRoleMode,
  publicTab: PublicTab,
  onSelectPublicTab: (PublicTab) -> Unit,
  clientTab: ClientTab,
  onSelectClientTab: (ClientTab) -> Unit,
  adminTab: AdminTab,
  onSelectAdminTab: (AdminTab) -> Unit
) {
  NavigationBar(
    containerColor = CharcoalSurface,
    tonalElevation = 8.dp,
    modifier = Modifier.height(64.dp)
  ) {
    when (currentRole) {
      AppRoleMode.PUBLIC_STUDIO -> {
        PublicTab.values().forEach { tab ->
          val selected = publicTab == tab
          NavigationBarItem(
            selected = selected,
            onClick = { onSelectPublicTab(tab) },
            icon = {
              Icon(
                imageVector = tab.icon,
                contentDescription = tab.title,
                modifier = Modifier.size(20.dp)
              )
            },
            label = {
              Text(
                text = tab.title,
                fontFamily = FontFamily.SansSerif,
                fontSize = 9.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
              )
            },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = CharcoalDark,
              selectedTextColor = GoldLight,
              indicatorColor = GoldPrimary,
              unselectedIconColor = MutedGrey,
              unselectedTextColor = MutedGrey
            ),
            modifier = Modifier.testTag("nav_${tab.name.lowercase()}")
          )
        }
      }
      AppRoleMode.CLIENT_PORTAL -> {
        ClientTab.values().forEach { tab ->
          val selected = clientTab == tab
          NavigationBarItem(
            selected = selected,
            onClick = { onSelectClientTab(tab) },
            icon = {
              Icon(
                imageVector = tab.icon,
                contentDescription = tab.title,
                modifier = Modifier.size(20.dp)
              )
            },
            label = {
              Text(
                text = tab.title,
                fontFamily = FontFamily.SansSerif,
                fontSize = 9.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
              )
            },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = CharcoalDark,
              selectedTextColor = GoldLight,
              indicatorColor = GoldPrimary,
              unselectedIconColor = MutedGrey,
              unselectedTextColor = MutedGrey
            ),
            modifier = Modifier.testTag("nav_client_${tab.name.lowercase()}")
          )
        }
      }
      AppRoleMode.ADMIN_AI -> {
        AdminTab.values().forEach { tab ->
          val selected = adminTab == tab
          NavigationBarItem(
            selected = selected,
            onClick = { onSelectAdminTab(tab) },
            icon = {
              Icon(
                imageVector = tab.icon,
                contentDescription = tab.title,
                modifier = Modifier.size(20.dp)
              )
            },
            label = {
              Text(
                text = tab.title,
                fontFamily = FontFamily.SansSerif,
                fontSize = 10.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
              )
            },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = CharcoalDark,
              selectedTextColor = GoldLight,
              indicatorColor = GoldPrimary,
              unselectedIconColor = MutedGrey,
              unselectedTextColor = MutedGrey
            ),
            modifier = Modifier.testTag("nav_admin_${tab.name.lowercase()}")
          )
        }
      }
    }
  }
}
