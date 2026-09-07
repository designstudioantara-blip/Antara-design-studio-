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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.MaterialSampleEntity
import com.example.ui.components.GoldButton
import com.example.ui.components.GoldOutlinedButton
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
fun MaterialStudioScreen(
  viewModel: MainViewModel,
  modifier: Modifier = Modifier
) {
  val materials by viewModel.materials.collectAsStateWithLifecycle()
  var selectedCategory by remember { mutableStateOf("All") }

  val categories = listOf("All", "Marble & Stone", "Veneers & Millwork", "Fabrics & Leather", "Hardware & Finishes", "Architectural Lighting", "Wall Finishes & Panels")

  val filteredMaterials = if (selectedCategory == "All") {
    materials
  } else {
    materials.filter { it.category.contains(selectedCategory, ignoreCase = true) || selectedCategory.contains(it.category, ignoreCase = true) }
  }

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
        badgeText = "Curated Finishes",
        title = "Material Selection Studio",
        subtitle = "Review, approve swatches, or request physical material sample boxes dispatched to your residence."
      )
    }

    // Category Filter Chips
    item {
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth().testTag("material_categories_row")
      ) {
        items(categories) { category ->
          val isSelected = selectedCategory == category
          Surface(
            shape = RoundedCornerShape(20.dp),
            color = if (isSelected) GoldPrimary else CharcoalElevated,
            border = BorderStroke(0.6.dp, if (isSelected) GoldLight else CharcoalBorder),
            modifier = Modifier
              .clickable { selectedCategory = category }
              .testTag("mat_chip_${category.take(6).lowercase()}")
          ) {
            Text(
              text = category,
              fontFamily = FontFamily.SansSerif,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
              fontSize = 11.5.sp,
              color = if (isSelected) CharcoalDark else PureWhite,
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
          }
        }
      }
    }

    // Material Cards
    items(filteredMaterials) { sample ->
      MaterialSampleCard(
        sample = sample,
        onToggleApproval = { viewModel.toggleMaterialApproval(sample.id, sample.isClientApproved) },
        onToggleSampleRequest = { viewModel.toggleMaterialSampleRequest(sample.id, sample.sampleRequested) }
      )
    }

    // Physical Sample Delivery Box Notice
    item {
      LuxuryCard(hasGoldAccent = true) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.LocalShipping,
              contentDescription = null,
              tint = GoldPrimary,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Bespoke Material Sample Box",
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              fontSize = 14.sp,
              color = PureWhite
            )
          }
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "Hand-cut Italian marble tiles, veneer swatches with PU sheen variants, and fabric cuts will be delivered to: Althan Canal Road, Surat.",
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            color = WarmGrey
          )
        }
      }
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}

@Composable
private fun MaterialSampleCard(
  sample: MaterialSampleEntity,
  onToggleApproval: () -> Unit,
  onToggleSampleRequest: () -> Unit
) {
  LuxuryCard(hasGoldAccent = sample.isClientApproved) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          StatusBadge(text = sample.category, type = StatusBadgeType.GOLD)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Tier: ${sample.costTier}",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = GoldLight
          )
        }

        StatusBadge(
          text = if (sample.isClientApproved) "Approved" else "Review Needed",
          type = if (sample.isClientApproved) StatusBadgeType.SUCCESS else StatusBadgeType.PENDING
        )
      }

      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = sample.name,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 15.5.sp,
        color = PureWhite
      )

      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "Brand / Quarry: ${sample.brandOrigin} • Target: ${sample.roomZone}",
        fontFamily = FontFamily.SansSerif,
        fontSize = 11.5.sp,
        color = GoldLight
      )

      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = sample.specification,
        fontFamily = FontFamily.SansSerif,
        fontSize = 12.sp,
        color = WarmGrey,
        lineHeight = 17.sp
      )

      Spacer(modifier = Modifier.height(14.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        if (sample.isClientApproved) {
          GoldOutlinedButton(
            text = "Approved (Tap to Revoke)",
            onClick = onToggleApproval,
            icon = Icons.Default.CheckCircle,
            modifier = Modifier.weight(1.2f)
          )
        } else {
          GoldButton(
            text = "Approve Finish",
            onClick = onToggleApproval,
            icon = Icons.Default.Check,
            modifier = Modifier.weight(1.2f)
          )
        }

        Surface(
          shape = RoundedCornerShape(8.dp),
          color = if (sample.sampleRequested) CharcoalElevated else CharcoalDark,
          border = BorderStroke(0.8.dp, if (sample.sampleRequested) GoldLight else CharcoalBorder),
          modifier = Modifier
            .weight(0.8f)
            .height(48.dp)
            .clickable { onToggleSampleRequest() }
            .testTag("sample_req_${sample.id}")
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
          ) {
            Icon(
              imageVector = if (sample.sampleRequested) Icons.Default.Inventory else Icons.Default.LocalShipping,
              contentDescription = null,
              tint = if (sample.sampleRequested) EmeraldSuccess else GoldLight,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = if (sample.sampleRequested) "Sample Sent" else "Request Box",
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.SemiBold,
              fontSize = 11.sp,
              color = if (sample.sampleRequested) EmeraldSuccess else PureWhite
            )
          }
        }
      }
    }
  }
}
