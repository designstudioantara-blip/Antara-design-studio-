package com.example.ui.screens.client

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.LuxuryCard
import com.example.ui.components.LuxurySectionHeader
import com.example.ui.components.StatusBadge
import com.example.ui.components.StatusBadgeType
import com.example.ui.theme.AmberPending
import com.example.ui.theme.CharcoalBorder
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalElevated
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.OffWhite
import com.example.ui.theme.PureWhite
import com.example.ui.theme.WarmGrey

data class TimelineStage(
  val order: Int,
  val title: String,
  val dateRange: String,
  val status: StageStatus,
  val supervisorNote: String,
  val qaChecklist: List<String>
)

enum class StageStatus { COMPLETED, IN_PROGRESS, UPCOMING }

@Composable
fun ProjectTimelineScreen(
  modifier: Modifier = Modifier
) {
  val stages = listOf(
    TimelineStage(
      order = 1,
      title = "Phase 1: Spatial Study & 3D Renders",
      dateRange = "12 Oct 2024 - 10 Nov 2024",
      status = StageStatus.COMPLETED,
      supervisorNote = "Client sign-off achieved on all 3D walk-through visuals and floor plans.",
      qaChecklist = listOf("2D Autocad drawings locked", "3D photoreal renders approved", "Structural load test passed")
    ),
    TimelineStage(
      order = 2,
      title = "Phase 2: Stone Quarry & Sourcing",
      dateRange = "15 Nov 2024 - 15 Dec 2024",
      status = StageStatus.COMPLETED,
      supervisorNote = "Italian Statuario slabs tagged and dry-layed at Surat stone yard. Veneers inspected.",
      qaChecklist = listOf("Marble moisture calibration", "Veneer grain alignment test", "PVD brass profile delivery")
    ),
    TimelineStage(
      order = 3,
      title = "Phase 3: Civil, MEP & Electrical",
      dateRange = "18 Dec 2024 - 20 Jan 2025",
      status = StageStatus.COMPLETED,
      supervisorNote = "Concealed conduit wiring, plumbing lines, and false ceiling framing completed.",
      qaChecklist = listOf("Laser leveling on false ceiling", "Pressure test on plumbing lines", "DALI lighting wiring verified")
    ),
    TimelineStage(
      order = 4,
      title = "Phase 4: Marble Laying & Polish",
      dateRange = "22 Jan 2025 - 15 Feb 2025",
      status = StageStatus.COMPLETED,
      supervisorNote = "Bookmatched Statuario mirror polish and epoxy grouting executed to perfection.",
      qaChecklist = listOf("Seamless zero-joint alignment", "Diamond pad mirror polish (90+ gloss)", "Protective corrugation sheet laid")
    ),
    TimelineStage(
      order = 5,
      title = "Phase 5: Carpentry, Millwork & Automation",
      dateRange = "18 Feb 2025 - 25 Mar 2025",
      status = StageStatus.IN_PROGRESS,
      supervisorNote = "Master walk-in wardrobe framing in progress. Flos magnetic lighting tracks installed.",
      qaChecklist = listOf("Blum motorized tandem alignment", "Smoked oak paneling fixing", "Smart home Lutron programming")
    ),
    TimelineStage(
      order = 6,
      title = "Phase 6: Styling & Golden Key Handover",
      dateRange = "28 Mar 2025 - 15 Apr 2025",
      status = StageStatus.UPCOMING,
      supervisorNote = "Curtain drape installation, art curation, deep cleaning, and client champagne handover.",
      qaChecklist = listOf("Air quality ionization & deep clean", "Full turnkey appliance demo", "Warranty certificate handover")
    )
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(CharcoalDark)
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item { Spacer(modifier = Modifier.height(6.dp)) }

    item {
      LuxurySectionHeader(
        badgeText = "Live Site Telemetry",
        title = "Execution Milestone Tracker",
        subtitle = "Real-time construction milestones and quality assurance logs for The Althan Sky Penthouse."
      )
    }

    // Stages List
    items(stages) { stage ->
      TimelineStageCard(stage = stage)
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}

@Composable
private fun TimelineStageCard(stage: TimelineStage) {
  val (statusText, statusType, icon) = when (stage.status) {
    StageStatus.COMPLETED -> Triple("Stage Completed", StatusBadgeType.SUCCESS, Icons.Default.CheckCircle)
    StageStatus.IN_PROGRESS -> Triple("Active In-Progress", StatusBadgeType.GOLD, Icons.Default.Engineering)
    StageStatus.UPCOMING -> Triple("Upcoming Stage", StatusBadgeType.PENDING, Icons.Default.Schedule)
  }

  LuxuryCard(hasGoldAccent = stage.status == StageStatus.IN_PROGRESS) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(24.dp)
              .clip(CircleShape)
              .background(
                when (stage.status) {
                  StageStatus.COMPLETED -> EmeraldSuccess
                  StageStatus.IN_PROGRESS -> GoldPrimary
                  StageStatus.UPCOMING -> CharcoalElevated
                }
              ),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "${stage.order}",
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.Bold,
              fontSize = 11.sp,
              color = if (stage.status == StageStatus.UPCOMING) WarmGrey else CharcoalDark
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = stage.dateRange,
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = MutedGrey
          )
        }

        StatusBadge(text = statusText, type = statusType)
      }

      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = stage.title,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 15.5.sp,
        color = PureWhite
      )

      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = stage.supervisorNote,
        fontFamily = FontFamily.SansSerif,
        fontSize = 12.sp,
        color = WarmGrey,
        lineHeight = 17.sp
      )

      Spacer(modifier = Modifier.height(12.dp))
      Text(
        text = "QUALITY ASSURANCE PROTOCOLS:",
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 9.5.sp,
        letterSpacing = 1.sp,
        color = GoldLight
      )
      Spacer(modifier = Modifier.height(4.dp))

      stage.qaChecklist.forEach { item ->
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(vertical = 1.dp)
        ) {
          Icon(
            imageVector = if (stage.status == StageStatus.COMPLETED) Icons.Default.Verified else Icons.Default.Check,
            contentDescription = null,
            tint = if (stage.status == StageStatus.COMPLETED) EmeraldSuccess else GoldLight,
            modifier = Modifier.size(13.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = item,
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.5.sp,
            color = OffWhite
          )
        }
      }
    }
  }
}
