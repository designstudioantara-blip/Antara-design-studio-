package com.example.ui.screens.public

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material.icons.filled.Weekend
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.GoldButton
import com.example.ui.components.GoldOutlinedButton
import com.example.ui.components.LuxuryCard
import com.example.ui.components.LuxurySectionHeader
import com.example.ui.components.StatusBadge
import com.example.ui.components.StatusBadgeType
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

data class StudioService(
  val title: String,
  val category: String,
  val summary: String,
  val icon: ImageVector,
  val deliverables: List<String>,
  val timelineEstimate: String
)

@Composable
fun ServicesScreen(
  onBookConsultation: () -> Unit,
  onCalculateEstimate: () -> Unit,
  modifier: Modifier = Modifier
) {
  val servicesList = listOf(
    StudioService(
      title = "Turnkey Residential Interiors",
      category = "Penthouses & Mansions",
      summary = "Complete end-to-end interior execution from bare shell to turnkey handover with single-point warranty.",
      icon = Icons.Default.Home,
      deliverables = listOf(
        "2D Space Planning & 3D Photoreal Render Sets",
        "Italian Marble Procurement & Monolith Cladding",
        "Designer False Ceilings with Concealed HVAC & Coves",
        "Custom Wardrobes, Modular Kitchens & Vanities",
        "Turnkey Decor Styling, Curtains & Lighting Setup"
      ),
      timelineEstimate = "3 to 6 Months Execution"
    ),
    StudioService(
      title = "Commercial & Retail Architecture",
      category = "Corporate & High-End Retail",
      summary = "High-impact brand environments designed to elevate prestige, productivity, and VIP customer experiences.",
      icon = Icons.Default.Architecture,
      deliverables = listOf(
        "Executive Boardrooms with Acoustic Wall Suede",
        "Smart Glass Partitions & Biometric Access Zones",
        "Custom Reception Desks & Monolithic Quartz Counters",
        "High CRI 98 Museum-Grade Showcase Lighting"
      ),
      timelineEstimate = "2 to 4 Months Execution"
    ),
    StudioService(
      title = "Bespoke Millwork & Joinery",
      category = "Artisanal Furniture Studio",
      summary = "In-house joinery facility crafting one-of-a-kind veneer furniture, bronze inlay accents, and motorized storage.",
      icon = Icons.Default.Weekend,
      deliverables = listOf(
        "Natural Dyed Veneers with Ultra-Matte PU finishes",
        "Blum motorized soft-close tandem systems",
        "Curved boucle sofas & bespoke dining tables",
        "Brass & champagne gold PVD framed vitrines"
      ),
      timelineEstimate = "Integrated with Project Milestones"
    ),
    StudioService(
      title = "Landscape & Biophilic Design",
      category = "Courtyards & Terraces",
      summary = "Harmonious outdoor-indoor living with stone water cascades, vertical greenery, and zen terraces.",
      icon = Icons.Default.Park,
      deliverables = listOf(
        "Vertical Hydroponic Living Wall Installations",
        "Raw Travertine & Slate Courtyard Fountains",
        "Weatherproof Teak & Rattan Lounge Cabanas",
        "Automated Drip Irrigation & Low-Voltage Mood Uplighting"
      ),
      timelineEstimate = "4 to 8 Weeks"
    ),
    StudioService(
      title = "3D VR Walkthroughs & BIM Planning",
      category = "Digital Architecture",
      summary = "Hyper-realistic spatial visualization so you experience materials, reflections, and daylight prior to civil execution.",
      icon = Icons.Default.ViewInAr,
      deliverables = listOf(
        "360-degree Virtual Reality Room Walkthroughs",
        "Exact Sun Angle & Circadian Light Simulation",
        "Millimeter-accurate MEP & Electrical Blueprints",
        "Comprehensive Material Moodboards & Physical Swatches"
      ),
      timelineEstimate = "10 to 14 Days Initial Turnaround"
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
        badgeText = "Studio Capabilities",
        title = "Artisanal Architecture & Interiors",
        subtitle = "A holistic turnkey approach where design purity meets meticulous civil craftsmanship."
      )
    }

    // 6-Phase Execution Workflow Banner
    item {
      StudioWorkflowCard()
    }

    // Services Breakdown List
    items(servicesList) { service ->
      ServiceDetailCard(service = service)
    }

    // Bottom CTA Card
    item {
      LuxuryCard(hasGoldAccent = true) {
        Column(
          modifier = Modifier.padding(18.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "Initiate Your Turnkey Project",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = PureWhite
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "Connect with our principal design architects at our Times World studio in Surat.",
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            color = MutedGrey
          )
          Spacer(modifier = Modifier.height(14.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            GoldButton(
              text = "Book Consultation",
              onClick = onBookConsultation,
              modifier = Modifier.weight(1f)
            )
            GoldOutlinedButton(
              text = "Calculate Budget",
              onClick = onCalculateEstimate,
              modifier = Modifier.weight(1f)
            )
          }
        }
      }
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}

@Composable
private fun StudioWorkflowCard() {
  LuxuryCard(hasGoldAccent = false) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "The Antara Turnkey Process",
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          fontSize = 15.sp,
          color = PureWhite
        )
        StatusBadge(text = "6 Standard Phases", type = StatusBadgeType.GOLD)
      }
      Spacer(modifier = Modifier.height(12.dp))

      val phases = listOf(
        "1. Discovery & Spatial Moodboard",
        "2. 3D Photoreal Render Approval",
        "3. Italian Stone & Material Sourcing",
        "4. Civil, Electrical & False Ceilings",
        "5. Joinery, Millwork & Automation",
        "6. Turnkey Styling & Golden Key Handover"
      )

      phases.forEachIndexed { index, phase ->
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(vertical = 3.dp)
        ) {
          Box(
            modifier = Modifier
              .size(18.dp)
              .clip(CircleShape)
              .background(CharcoalElevated),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "${index + 1}",
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.Bold,
              fontSize = 10.sp,
              color = GoldPrimary
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = phase,
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            color = WarmGrey
          )
        }
      }
    }
  }
}

@Composable
private fun ServiceDetailCard(service: StudioService) {
  LuxuryCard(hasGoldAccent = false) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(RoundedCornerShape(8.dp))
              .background(CharcoalElevated),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = service.icon,
              contentDescription = null,
              tint = GoldPrimary,
              modifier = Modifier.size(20.dp)
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = service.title,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              fontSize = 15.sp,
              color = PureWhite
            )
            Text(
              text = service.category,
              fontFamily = FontFamily.SansSerif,
              fontSize = 11.sp,
              color = GoldLight
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = service.summary,
        fontFamily = FontFamily.SansSerif,
        fontSize = 12.5.sp,
        color = WarmGrey,
        lineHeight = 17.sp
      )

      Spacer(modifier = Modifier.height(12.dp))
      Text(
        text = "INCLUDED DELIVERABLES:",
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        letterSpacing = 1.sp,
        color = GoldMuted
      )
      Spacer(modifier = Modifier.height(6.dp))

      service.deliverables.forEach { item ->
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(vertical = 2.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = EmeraldSuccess,
            modifier = Modifier.size(14.dp)
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

      Spacer(modifier = Modifier.height(10.dp))
      Surface(
        color = CharcoalElevated,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.Engineering,
            contentDescription = null,
            tint = GoldLight,
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Typical Duration: ${service.timelineEstimate}",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            color = GoldLight
          )
        }
      }
    }
  }
}
