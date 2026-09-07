package com.example.ui.screens.client

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.GoldButton
import com.example.ui.components.GoldOutlinedButton
import com.example.ui.components.LinearProgressWithGold
import com.example.ui.components.LuxuryCard
import com.example.ui.components.LuxurySectionHeader
import com.example.ui.components.StatusBadge
import com.example.ui.components.StatusBadgeType
import com.example.ui.theme.AmberPending
import com.example.ui.theme.CharcoalBorder
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalElevated
import com.example.ui.theme.CharcoalSurface
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.OffWhite
import com.example.ui.theme.PureWhite
import com.example.ui.theme.WarmGrey
import com.example.ui.viewmodel.MainViewModel

@Composable
fun ClientDashboardScreen(
  viewModel: MainViewModel,
  onNavigateToTimeline: () -> Unit,
  onNavigateToMaterials: () -> Unit,
  onNavigateToInvoices: () -> Unit,
  onNavigateToChat: () -> Unit,
  onNavigateToDocs: () -> Unit,
  modifier: Modifier = Modifier
) {
  val projects by viewModel.projects.collectAsStateWithLifecycle()
  val activeProject = projects.firstOrNull { it.id == 1L } ?: projects.firstOrNull()

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(CharcoalDark)
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item { Spacer(modifier = Modifier.height(6.dp)) }

    // Client Welcome Header
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Welcome Back,",
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            color = GoldLight
          )
          Text(
            text = activeProject?.clientName ?: "VIP Client",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = PureWhite
          )
        }

        StatusBadge(text = "Active Turnkey Project", type = StatusBadgeType.SUCCESS)
      }
    }

    // Active Project Progress Card
    item {
      if (activeProject != null) {
        ActiveProjectHeroCard(
          project = activeProject,
          onViewTimeline = onNavigateToTimeline
        )
      }
    }

    // Action Required / Approvals Strip
    item {
      PendingActionsCard(
        onApproveMaterial = onNavigateToMaterials,
        onPayMilestone = onNavigateToInvoices
      )
    }

    // Client Management Quick Hub Grid
    item {
      Column {
        Text(
          text = "CLIENT PORTAL MODULES",
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          fontSize = 11.sp,
          letterSpacing = 1.sp,
          color = GoldLight
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          PortalActionTile(
            title = "Project Timeline",
            subtitle = "Stage 5 of 6",
            icon = Icons.Default.Timeline,
            onClick = onNavigateToTimeline,
            modifier = Modifier.weight(1f)
          )
          PortalActionTile(
            title = "Material Studio",
            subtitle = "Swatches & finishes",
            icon = Icons.Default.Layers,
            onClick = onNavigateToMaterials,
            modifier = Modifier.weight(1f)
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          PortalActionTile(
            title = "Milestone Bills",
            subtitle = "Phase 3 pending",
            icon = Icons.Default.CreditCard,
            onClick = onNavigateToInvoices,
            modifier = Modifier.weight(1f)
          )
          PortalActionTile(
            title = "Studio Chat",
            subtitle = "Direct to architect",
            icon = Icons.Default.Chat,
            onClick = onNavigateToChat,
            modifier = Modifier.weight(1f)
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        PortalActionTile(
          title = "Blueprint Vault & 3D Render Pack",
          subtitle = "Access 2D AutoCAD layouts, electrical drawings & contracts",
          icon = Icons.Default.Folder,
          onClick = onNavigateToDocs,
          modifier = Modifier.fillMaxWidth()
        )
      }
    }

    // Dedicated Team Information
    item {
      LuxuryCard(hasGoldAccent = false) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "ASSIGNED ANTARA STUDIO TEAM",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            color = GoldLight
          )
          Spacer(modifier = Modifier.height(12.dp))

          TeamMemberRow(
            name = activeProject?.leadDesigner ?: "Ar. Hardik Patel",
            role = "Lead Architect & Spatial Designer",
            contact = "Direct studio extension #104"
          )
          Spacer(modifier = Modifier.height(10.dp))
          TeamMemberRow(
            name = activeProject?.projectManager ?: "Er. Sunny Shah",
            role = "Senior Site Project Manager",
            contact = "On-site supervisor Althan"
          )
        }
      }
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}

@Composable
private fun ActiveProjectHeroCard(
  project: com.example.data.local.ProjectEntity,
  onViewTimeline: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .background(
        Brush.verticalGradient(
          listOf(CharcoalElevated, CharcoalCard)
        )
      )
      .border(1.dp, GoldPrimary, RoundedCornerShape(14.dp))
      .padding(18.dp)
  ) {
    Column {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = project.spaceType.uppercase(),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            letterSpacing = 1.2.sp,
            color = GoldLight
          )
          Text(
            text = project.title,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = PureWhite
          )
        }
        StatusBadge(text = "${project.progressPercent}% DONE", type = StatusBadgeType.GOLD)
      }

      Spacer(modifier = Modifier.height(12.dp))
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.LocationOn,
          contentDescription = null,
          tint = GoldLight,
          modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = project.location,
          fontFamily = FontFamily.SansSerif,
          fontSize = 11.5.sp,
          color = WarmGrey
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
          text = "• ${project.totalAreaSqFt} Sq. Ft.",
          fontFamily = FontFamily.SansSerif,
          fontSize = 11.5.sp,
          color = WarmGrey
        )
      }

      Spacer(modifier = Modifier.height(14.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Current Stage: ${project.currentPhase}",
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.SemiBold,
          fontSize = 12.sp,
          color = GoldLight
        )
        Text(
          text = "Target Handover: ${project.targetHandoverDate}",
          fontFamily = FontFamily.SansSerif,
          fontSize = 11.sp,
          color = MutedGrey
        )
      }

      Spacer(modifier = Modifier.height(6.dp))
      LinearProgressWithGold(progress = project.progressPercent / 100f)

      Spacer(modifier = Modifier.height(14.dp))
      GoldButton(
        text = "View Stage Timeline & Site Logs",
        onClick = onViewTimeline,
        icon = Icons.Default.Timeline,
        modifier = Modifier.fillMaxWidth()
      )
    }
  }
}

@Composable
private fun PendingActionsCard(
  onApproveMaterial: () -> Unit,
  onPayMilestone: () -> Unit
) {
  LuxuryCard(hasGoldAccent = true) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = null,
            tint = AmberPending,
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "PENDING CLIENT ACTIONS",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            color = PureWhite
          )
        }
        StatusBadge(text = "2 Actions", type = StatusBadgeType.PENDING)
      }

      Spacer(modifier = Modifier.height(10.dp))
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onApproveMaterial() }
          .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(AmberPending))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "Review & Approve: Dedar Milano Velvet Boucle Swatch",
          fontFamily = FontFamily.SansSerif,
          fontSize = 12.sp,
          color = WarmGrey,
          modifier = Modifier.weight(1f)
        )
        Text(text = "Review", fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = GoldLight)
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onPayMilestone() }
          .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(AmberPending))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "Phase 3 Milestone Billing: ₹17.0 Lakhs Due",
          fontFamily = FontFamily.SansSerif,
          fontSize = 12.sp,
          color = WarmGrey,
          modifier = Modifier.weight(1f)
        )
        Text(text = "Pay", fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = GoldLight)
      }
    }
  }
}

@Composable
private fun PortalActionTile(
  title: String,
  subtitle: String,
  icon: ImageVector,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  LuxuryCard(
    modifier = modifier,
    onClick = onClick,
    hasGoldAccent = false
  ) {
    Row(
      modifier = Modifier.padding(14.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(36.dp)
          .clip(RoundedCornerShape(8.dp))
          .background(CharcoalElevated)
          .border(0.6.dp, GoldPrimary, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = GoldLight,
          modifier = Modifier.size(18.dp)
        )
      }
      Spacer(modifier = Modifier.width(10.dp))
      Column {
        Text(
          text = title,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          fontSize = 13.5.sp,
          color = PureWhite
        )
        Text(
          text = subtitle,
          fontFamily = FontFamily.SansSerif,
          fontSize = 11.sp,
          color = MutedGrey
        )
      }
    }
  }
}

@Composable
private fun TeamMemberRow(name: String, role: String, contact: String) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Box(
      modifier = Modifier
        .size(34.dp)
        .clip(CircleShape)
        .background(CharcoalElevated)
        .border(0.5.dp, GoldPrimary, CircleShape),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = Icons.Default.Person,
        contentDescription = null,
        tint = GoldLight,
        modifier = Modifier.size(18.dp)
      )
    }
    Spacer(modifier = Modifier.width(10.dp))
    Column {
      Text(
        text = name,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 13.5.sp,
        color = PureWhite
      )
      Text(
        text = "$role • $contact",
        fontFamily = FontFamily.SansSerif,
        fontSize = 11.sp,
        color = WarmGrey
      )
    }
  }
}
