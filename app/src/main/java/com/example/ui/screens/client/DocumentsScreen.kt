package com.example.ui.screens.client

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FolderZip
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.GoldButton
import com.example.ui.components.LuxuryCard
import com.example.ui.components.LuxurySectionHeader
import com.example.ui.components.StatusBadge
import com.example.ui.components.StatusBadgeType
import com.example.ui.theme.CharcoalBorder
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalElevated
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.OffWhite
import com.example.ui.theme.PureWhite
import com.example.ui.theme.WarmGrey

data class StudioDocument(
  val title: String,
  val category: String,
  val fileSize: String,
  val dateUpdated: String,
  val icon: ImageVector
)

@Composable
fun DocumentsScreen(
  modifier: Modifier = Modifier
) {
  val blueprints = listOf(
    StudioDocument("2D Architectural Layout & Zoning (Rev 4)", "AutoCAD DWG / PDF", "14.2 MB", "15 Jan 2025", Icons.Default.Architecture),
    StudioDocument("Reflected False Ceiling & HVAC Ducting Plan", "AutoCAD MEP / PDF", "9.8 MB", "20 Jan 2025", Icons.Default.Architecture),
    StudioDocument("DALI Smart Lighting & Circuit Schematics", "Electrical Schematics", "6.4 MB", "22 Jan 2025", Icons.Default.Architecture),
    StudioDocument("Custom Wardrobe & Millwork Joinery Details", "Fabrication Drawings", "18.5 MB", "10 Feb 2025", Icons.Default.Architecture)
  )

  val renders = listOf(
    StudioDocument("Living & Dining Grand Salon (Day & Night 4K)", "3D Photoreal Render Pack", "48.0 MB", "12 Nov 2024", Icons.Default.Image),
    StudioDocument("Master Suite & Walk-in Wardrobe 360 VR", "VR Panoramic Walkthrough", "84.2 MB", "18 Nov 2024", Icons.Default.Image),
    StudioDocument("Terrace Zen Lounge & Biophilic Fountain", "Landscape 3D Set", "32.1 MB", "22 Nov 2024", Icons.Default.Image)
  )

  val contracts = listOf(
    StudioDocument("Antara Turnkey Interior Execution Agreement", "Signed Legal Contract", "4.2 MB", "12 Oct 2024", Icons.Default.Description),
    StudioDocument("Italian Statuario Quarry Authenticity Certificate", "Stone Provenance", "2.1 MB", "15 Dec 2024", Icons.Default.Shield),
    StudioDocument("5-Year Comprehensive Structural & Hardware Warranty", "Warranty Certificate", "1.8 MB", "Handover Pack", Icons.Default.Shield)
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
        badgeText = "Digital Blueprint Vault",
        title = "Plans, 3D Renders & Contracts",
        subtitle = "Centralized cloud repository for all architectural drawings, warranties, and high-res media."
      )
    }

    // Blueprints Section
    item {
      DocumentGroupSection(title = "ARCHITECTURAL BLUEPRINTS & MEP", documents = blueprints)
    }

    // 3D Renders Section
    item {
      DocumentGroupSection(title = "3D PHOTOREAL RENDERS & VR PACKS", documents = renders)
    }

    // Legal Contracts & Warranty
    item {
      DocumentGroupSection(title = "LEGAL CONTRACTS & WARRANTY", documents = contracts)
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}

@Composable
private fun DocumentGroupSection(
  title: String,
  documents: List<StudioDocument>
) {
  Column {
    Text(
      text = title,
      fontFamily = FontFamily.SansSerif,
      fontWeight = FontWeight.Bold,
      fontSize = 11.sp,
      letterSpacing = 1.sp,
      color = GoldLight
    )
    Spacer(modifier = Modifier.height(8.dp))

    LuxuryCard(hasGoldAccent = false) {
      Column(modifier = Modifier.padding(12.dp)) {
        documents.forEachIndexed { index, doc ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { /* Simulate download/view */ }
              .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(CharcoalElevated),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = doc.icon,
                contentDescription = null,
                tint = GoldLight,
                modifier = Modifier.size(18.dp)
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = doc.title,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = PureWhite
              )
              Text(
                text = "${doc.category} • ${doc.fileSize} • Updated ${doc.dateUpdated}",
                fontFamily = FontFamily.SansSerif,
                fontSize = 10.5.sp,
                color = MutedGrey
              )
            }
            Icon(
              imageVector = Icons.Default.Download,
              contentDescription = "Download",
              tint = GoldPrimary,
              modifier = Modifier.size(18.dp)
            )
          }

          if (index < documents.size - 1) {
            Box(modifier = Modifier.fillMaxWidth().height(0.6.dp).background(CharcoalBorder))
          }
        }
      }
    }
  }
}
