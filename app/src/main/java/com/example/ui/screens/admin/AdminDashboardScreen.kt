package com.example.ui.screens.admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.ConsultationEntity
import com.example.data.local.ProjectEntity
import com.example.ui.components.LuxuryCard
import com.example.ui.components.LuxurySectionHeader
import com.example.ui.components.StatusBadge
import com.example.ui.components.StatusBadgeType
import com.example.ui.theme.AmberPending
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalElevated
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.PureWhite
import com.example.ui.theme.WarmGrey
import com.example.ui.viewmodel.MainViewModel

@Composable
fun AdminDashboardScreen(
  viewModel: MainViewModel,
  modifier: Modifier = Modifier
) {
  val bookings by viewModel.consultations.collectAsStateWithLifecycle()
  val projects by viewModel.projects.collectAsStateWithLifecycle()

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(CharcoalDark)
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item { Spacer(modifier = Modifier.height(6.dp)) }

    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "STUDIO MANAGEMENT CONSOLE",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 10.5.sp,
            letterSpacing = 1.2.sp,
            color = GoldLight
          )
          Text(
            text = "Executive Overview",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = PureWhite
          )
        }
        StatusBadge(text = "Principal Access", type = StatusBadgeType.GOLD)
      }
    }

    // Studio KPIs Grid
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        KpiMetricCard(
          title = "ACTIVE SITES",
          value = "${projects.size}",
          subtitle = "Under execution",
          modifier = Modifier.weight(1f)
        )
        KpiMetricCard(
          title = "PIPELINE VALUE",
          value = "₹3.85 Cr",
          subtitle = "Q1 2025 Target",
          modifier = Modifier.weight(1f)
        )
      }
    }

    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        KpiMetricCard(
          title = "INBOUND LEADS",
          value = "${bookings.size}",
          subtitle = "Surat & Gujarat",
          modifier = Modifier.weight(1f)
        )
        KpiMetricCard(
          title = "HANDOVER RATE",
          value = "100%",
          subtitle = "On-schedule track",
          modifier = Modifier.weight(1f)
        )
      }
    }

    // Inbound Inquiries & Consultations CRM
    item {
      Column {
        LuxurySectionHeader(
          badgeText = "Client CRM",
          title = "Consultation Inquiries (${bookings.size})",
          subtitle = "Tap status badge to advance lead pipeline stages."
        )

        Spacer(modifier = Modifier.height(10.dp))

        bookings.forEach { booking ->
          LeadCrmCard(
            booking = booking,
            onCycleStatus = {
              val nextStatus = when (booking.status) {
                "Confirmed" -> "Site Visited"
                "Site Visited" -> "Proposal Sent"
                "Proposal Sent" -> "Contract Signed"
                else -> "Confirmed"
              }
              viewModel.updateConsultationStatus(booking.id, nextStatus)
            }
          )
          Spacer(modifier = Modifier.height(8.dp))
        }
      }
    }

    // Active Turnkey Projects Status
    item {
      Column {
        LuxurySectionHeader(
          badgeText = "Project Oversight",
          title = "Site Construction Status",
          subtitle = "Progress across residential mansions, villas, and commercial offices."
        )

        Spacer(modifier = Modifier.height(10.dp))

        projects.forEach { project ->
          AdminProjectRowCard(project = project)
          Spacer(modifier = Modifier.height(8.dp))
        }
      }
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}

@Composable
private fun KpiMetricCard(
  title: String,
  value: String,
  subtitle: String,
  modifier: Modifier = Modifier
) {
  LuxuryCard(modifier = modifier, hasGoldAccent = false) {
    Column(modifier = Modifier.padding(14.dp)) {
      Text(
        text = title,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 9.5.sp,
        letterSpacing = 1.sp,
        color = GoldMuted
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = value,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = PureWhite
      )
      Spacer(modifier = Modifier.height(2.dp))
      Text(
        text = subtitle,
        fontFamily = FontFamily.SansSerif,
        fontSize = 10.5.sp,
        color = WarmGrey
      )
    }
  }
}

@Composable
private fun LeadCrmCard(
  booking: ConsultationEntity,
  onCycleStatus: () -> Unit
) {
  LuxuryCard(hasGoldAccent = booking.status == "Confirmed") {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = booking.clientName,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 14.5.sp,
            color = PureWhite
          )
          Text(
            text = "${booking.consultationType} • ${booking.preferredDate} (${booking.preferredTimeSlot})",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = GoldLight
          )
        }

        Surface(
          shape = RoundedCornerShape(12.dp),
          color = when (booking.status) {
            "Contract Signed" -> EmeraldSuccess
            "Proposal Sent" -> GoldPrimary
            "Site Visited" -> CharcoalElevated
            else -> AmberPending
          },
          modifier = Modifier.clickable { onCycleStatus() }.testTag("lead_status_${booking.id}")
        ) {
          Text(
            text = "${booking.status} ▾",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 10.5.sp,
            color = if (booking.status == "Site Visited") PureWhite else CharcoalDark,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = "Phone: ${booking.clientPhone}",
          fontFamily = FontFamily.SansSerif,
          fontSize = 11.sp,
          color = WarmGrey
        )
        Text(
          text = "Budget: ${booking.budgetRange}",
          fontFamily = FontFamily.SansSerif,
          fontSize = 11.sp,
          color = GoldLight
        )
      }

      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "Property: ${booking.spaceType} (${booking.approximateSqFt} sq ft) • ${booking.siteAddress}",
        fontFamily = FontFamily.SansSerif,
        fontSize = 11.sp,
        color = MutedGrey
      )
    }
  }
}

@Composable
private fun AdminProjectRowCard(project: ProjectEntity) {
  LuxuryCard(hasGoldAccent = false) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = project.title,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = PureWhite
          )
          Text(
            text = "${project.clientName} • ${project.location}",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = WarmGrey
          )
        }
        StatusBadge(text = "${project.progressPercent}%", type = StatusBadgeType.GOLD)
      }

      Spacer(modifier = Modifier.height(6.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = "Phase: ${project.currentPhase}",
          fontFamily = FontFamily.SansSerif,
          fontSize = 11.sp,
          color = GoldLight
        )
        Text(
          text = "Target: ${project.targetHandoverDate}",
          fontFamily = FontFamily.SansSerif,
          fontSize = 10.5.sp,
          color = MutedGrey
        )
      }
    }
  }
}
