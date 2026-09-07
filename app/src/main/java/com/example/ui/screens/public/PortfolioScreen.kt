package com.example.ui.screens.public

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.PortfolioItemEntity
import com.example.ui.components.BeforeAfterVisualizer
import com.example.ui.components.GoldButton
import com.example.ui.components.LuxuryCard
import com.example.ui.components.LuxurySectionHeader
import com.example.ui.components.StatusBadge
import com.example.ui.components.StatusBadgeType
import com.example.ui.theme.CharcoalBorder
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalElevated
import com.example.ui.theme.CharcoalSurface
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.OffWhite
import com.example.ui.theme.PureWhite
import com.example.ui.theme.WarmGrey
import com.example.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioScreen(
  viewModel: MainViewModel,
  onBookConsultation: () -> Unit,
  modifier: Modifier = Modifier
) {
  val portfolioItems by viewModel.portfolioList.collectAsStateWithLifecycle()
  val selectedCategory by viewModel.selectedPortfolioCategory.collectAsStateWithLifecycle()
  var activeDetailItem by remember { mutableStateOf<PortfolioItemEntity?>(null) }

  val categories = listOf("All", "Penthouse", "Villa", "Commercial", "Residential")

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
        badgeText = "Curated Works",
        title = "Architectural Portfolio",
        subtitle = "A retrospective of bespoke residential sanctuaries and commercial spaces in Surat."
      )
    }

    // Category Filter Chips
    item {
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth().testTag("portfolio_categories_row")
      ) {
        items(categories) { category ->
          val isSelected = selectedCategory == category
          Surface(
            shape = RoundedCornerShape(20.dp),
            color = if (isSelected) GoldPrimary else CharcoalElevated,
            border = BorderStroke(0.6.dp, if (isSelected) GoldLight else CharcoalBorder),
            modifier = Modifier
              .clickable { viewModel.setPortfolioCategory(category) }
              .testTag("filter_chip_$category")
          ) {
            Text(
              text = category,
              fontFamily = FontFamily.SansSerif,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
              fontSize = 12.sp,
              color = if (isSelected) CharcoalDark else PureWhite,
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
            )
          }
        }
      }
    }

    // Project Cards List
    items(portfolioItems) { item ->
      PortfolioCard(
        item = item,
        onDetailsClick = { activeDetailItem = item }
      )
    }

    item {
      LuxuryCard(hasGoldAccent = true) {
        Column(
          modifier = Modifier.padding(18.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "Envision Your Space with Antara",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = PureWhite
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Every project begins with an in-depth spatial assessment and curated 3D visualization.",
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            color = MutedGrey
          )
          Spacer(modifier = Modifier.height(12.dp))
          GoldButton(
            text = "Schedule Spatial Consultation",
            onClick = onBookConsultation,
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }

  // Detail Modal Dialog
  if (activeDetailItem != null) {
    val item = activeDetailItem!!
    BasicAlertDialog(
      onDismissRequest = { activeDetailItem = null }
    ) {
      Surface(
        shape = RoundedCornerShape(16.dp),
        color = CharcoalSurface,
        border = BorderStroke(1.dp, GoldPrimary),
        modifier = Modifier.fillMaxWidth().padding(16.dp)
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            StatusBadge(text = item.category, type = StatusBadgeType.GOLD)
            IconButton(
              onClick = { activeDetailItem = null },
              modifier = Modifier.size(28.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = GoldLight
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = item.title,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = PureWhite
          )
          Text(
            text = "${item.location} • ${item.areaSqFt} Sq. Ft. • Handover ${item.completionYear}",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.5.sp,
            color = GoldLight
          )

          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = item.description,
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.5.sp,
            lineHeight = 18.sp,
            color = WarmGrey
          )

          Spacer(modifier = Modifier.height(14.dp))
          Text(
            text = "MATERIAL SPECIFICATIONS & CRAFT:",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            letterSpacing = 1.sp,
            color = GoldLight
          )
          Spacer(modifier = Modifier.height(6.dp))
          item.keyFeaturesJson.split(",").forEach { feature ->
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(vertical = 2.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(4.dp)
                  .clip(RoundedCornerShape(2.dp))
                  .background(GoldPrimary)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = feature.trim(),
                fontFamily = FontFamily.SansSerif,
                fontSize = 11.5.sp,
                color = OffWhite
              )
            }
          }

          Spacer(modifier = Modifier.height(18.dp))
          GoldButton(
            text = "Inquire About Similar Design",
            onClick = {
              activeDetailItem = null
              onBookConsultation()
            },
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
    }
  }
}

@Composable
private fun PortfolioCard(
  item: PortfolioItemEntity,
  onDetailsClick: () -> Unit
) {
  LuxuryCard(hasGoldAccent = item.isFeatured) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          StatusBadge(text = item.category, type = StatusBadgeType.GOLD)
          Spacer(modifier = Modifier.width(8.dp))
          if (item.isFeatured) {
            StatusBadge(text = "Signature Work", type = StatusBadgeType.SUCCESS)
          }
        }
        Text(
          text = item.completionYear,
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Medium,
          fontSize = 11.sp,
          color = MutedGrey
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = item.title,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        color = PureWhite
      )

      Spacer(modifier = Modifier.height(4.dp))

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = GoldLight,
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(3.dp))
          Text(
            text = item.location,
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = WarmGrey
          )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.SquareFoot,
            contentDescription = null,
            tint = GoldLight,
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(3.dp))
          Text(
            text = "${item.areaSqFt} sq ft",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = WarmGrey
          )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Style,
            contentDescription = null,
            tint = GoldLight,
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(3.dp))
          Text(
            text = item.style,
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = GoldLight
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = item.tagline,
        fontFamily = FontFamily.SansSerif,
        fontSize = 12.sp,
        lineHeight = 17.sp,
        color = MutedGrey
      )

      Spacer(modifier = Modifier.height(12.dp))

      // Before/After interactive visualizer
      BeforeAfterVisualizer(
        beforeDescription = item.beforeImageDesc,
        afterDescription = item.afterImageDesc
      )

      Spacer(modifier = Modifier.height(10.dp))

      Surface(
        color = CharcoalElevated,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(0.5.dp, CharcoalBorder),
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onDetailsClick() }
          .padding(vertical = 2.dp)
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "View Architecture Specs & Blueprint Notes",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            color = GoldLight
          )
          Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = GoldLight,
            modifier = Modifier.size(14.dp)
          )
        }
      }
    }
  }
}
