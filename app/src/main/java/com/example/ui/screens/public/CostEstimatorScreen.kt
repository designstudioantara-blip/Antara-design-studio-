package com.example.ui.screens.public

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.FinishTier
import com.example.data.model.SpaceType
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
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldGradientEnd
import com.example.ui.theme.GoldGradientStart
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.OffWhite
import com.example.ui.theme.PureWhite
import com.example.ui.theme.WarmGrey
import com.example.ui.viewmodel.MainViewModel

@Composable
fun CostEstimatorScreen(
  viewModel: MainViewModel,
  onProceedToBooking: () -> Unit,
  modifier: Modifier = Modifier
) {
  val selectedSpaceType by viewModel.estimatorSpaceType.collectAsStateWithLifecycle()
  val currentSqFt by viewModel.estimatorSqFt.collectAsStateWithLifecycle()
  val selectedTier by viewModel.estimatorTier.collectAsStateWithLifecycle()
  val selectedRooms by viewModel.selectedRooms.collectAsStateWithLifecycle()
  val calculatedLakhs by viewModel.calculatedEstimateInLakhs.collectAsStateWithLifecycle()

  val allRooms = listOf(
    "Living & Dining Salon",
    "Master Bedroom Suite",
    "Modular Gourmet Kitchen",
    "Designer False Ceilings",
    "Smart Automation",
    "Balcony / Terrace Lounge",
    "Walk-in Wardrobe Dressing",
    "Home Theater / Audio Lounge"
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
        badgeText = "Interactive Planner",
        title = "Turnkey Cost Estimator",
        subtitle = "Calculate instant bespoke interior & architecture estimates tailored to Surat luxury standards."
      )
    }

    // Dynamic Live Result Showcase
    item {
      LiveEstimateDisplayCard(
        totalLakhs = calculatedLakhs,
        sqFt = currentSqFt,
        tier = selectedTier,
        onBookWithEstimate = onProceedToBooking
      )
    }

    // 1. Select Space Type
    item {
      Column {
        Text(
          text = "1. SELECT PROPERTY TYPE",
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          fontSize = 11.sp,
          letterSpacing = 1.sp,
          color = GoldLight
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          items(SpaceType.values()) { type ->
            val isSelected = selectedSpaceType == type
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = if (isSelected) GoldPrimary else CharcoalElevated,
              border = BorderStroke(0.6.dp, if (isSelected) GoldLight else CharcoalBorder),
              modifier = Modifier
                .clickable { viewModel.setEstimatorSpaceType(type) }
                .testTag("space_type_${type.name.lowercase()}")
            ) {
              Text(
                text = type.displayName,
                fontFamily = FontFamily.SansSerif,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 11.5.sp,
                color = if (isSelected) CharcoalDark else PureWhite,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
              )
            }
          }
        }
      }
    }

    // 2. Square Footage Slider
    item {
      LuxuryCard(hasGoldAccent = false) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "2. CARPET / BUILT-UP AREA",
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.Bold,
              fontSize = 11.sp,
              letterSpacing = 1.sp,
              color = GoldLight
            )
            Text(
              text = "$currentSqFt Sq. Ft.",
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp,
              color = PureWhite
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          Slider(
            value = currentSqFt.toFloat(),
            onValueChange = { viewModel.setEstimatorSqFt(it.toInt()) },
            valueRange = 800f..8000f,
            steps = 35,
            colors = SliderDefaults.colors(
              thumbColor = GoldPrimary,
              activeTrackColor = GoldLight,
              inactiveTrackColor = CharcoalElevated
            ),
            modifier = Modifier.testTag("sqft_slider")
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "800 sq ft (Compact)", fontFamily = FontFamily.SansSerif, fontSize = 10.sp, color = MutedGrey)
            Text(text = "8,000 sq ft (Grand Villa)", fontFamily = FontFamily.SansSerif, fontSize = 10.sp, color = MutedGrey)
          }
        }
      }
    }

    // 3. Finish Level & Specification Tiers
    item {
      Column {
        Text(
          text = "3. SELECT FINISH SPECIFICATION TIER",
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          fontSize = 11.sp,
          letterSpacing = 1.sp,
          color = GoldLight
        )
        Spacer(modifier = Modifier.height(8.dp))

        FinishTier.values().forEach { tier ->
          val isSelected = selectedTier == tier
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 4.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(if (isSelected) CharcoalSurface else CharcoalCard)
              .border(
                1.dp,
                if (isSelected) GoldPrimary else CharcoalBorder,
                RoundedCornerShape(10.dp)
              )
              .clickable { viewModel.setEstimatorTier(tier) }
              .testTag("tier_${tier.name.lowercase()}")
              .padding(14.dp)
          ) {
            Column {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Box(
                    modifier = Modifier
                      .size(16.dp)
                      .clip(CircleShape)
                      .background(if (isSelected) GoldPrimary else Color.Transparent)
                      .border(1.dp, GoldPrimary, CircleShape),
                    contentAlignment = Alignment.Center
                  ) {
                    if (isSelected) {
                      Box(
                        modifier = Modifier
                          .size(6.dp)
                          .clip(CircleShape)
                          .background(CharcoalDark)
                      )
                    }
                  }
                  Spacer(modifier = Modifier.width(8.dp))
                  Text(
                    text = tier.title,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.5.sp,
                    color = PureWhite
                  )
                }

                StatusBadge(
                  text = if (tier == FinishTier.BESPOKE_HAUTE_COUTURE) "Masterpiece" else "Popular Choice",
                  type = if (isSelected) StatusBadgeType.GOLD else StatusBadgeType.INFO
                )
              }

              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = tier.tagline,
                fontFamily = FontFamily.SansSerif,
                fontSize = 11.5.sp,
                color = GoldLight
              )

              Spacer(modifier = Modifier.height(8.dp))
              tier.inclusions.take(3).forEach { inc ->
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.padding(vertical = 1.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = EmeraldSuccess,
                    modifier = Modifier.size(12.dp)
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(
                    text = inc,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 11.sp,
                    color = WarmGrey
                  )
                }
              }
            }
          }
        }
      }
    }

    // 4. Room Scope Inclusions
    item {
      LuxuryCard(hasGoldAccent = false) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "4. INCLUDED SPATIAL SCOPE (${selectedRooms.size} SELECTED)",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            color = GoldLight
          )
          Spacer(modifier = Modifier.height(8.dp))

          allRooms.chunked(2).forEach { pair ->
            Row(modifier = Modifier.fillMaxWidth()) {
              pair.forEach { room ->
                val checked = selectedRooms.contains(room)
                Row(
                  modifier = Modifier
                    .weight(1f)
                    .clickable { viewModel.toggleEstimatorRoom(room) }
                    .padding(vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Checkbox(
                    checked = checked,
                    onCheckedChange = { viewModel.toggleEstimatorRoom(room) },
                    colors = CheckboxDefaults.colors(
                      checkedColor = GoldPrimary,
                      uncheckedColor = MutedGrey,
                      checkmarkColor = CharcoalDark
                    ),
                    modifier = Modifier.size(24.dp)
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(
                    text = room,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 11.sp,
                    color = if (checked) PureWhite else MutedGrey,
                    lineHeight = 14.sp
                  )
                }
              }
            }
          }
        }
      }
    }

    // Milestone Payment Breakdown
    item {
      MilestoneBreakdownPreview(totalLakhs = calculatedLakhs)
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}

@Composable
private fun LiveEstimateDisplayCard(
  totalLakhs: Double,
  sqFt: Int,
  tier: FinishTier,
  onBookWithEstimate: () -> Unit
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
            text = "ESTIMATED TURNKEY BUDGET",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            letterSpacing = 1.2.sp,
            color = GoldLight
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "₹${String.format("%.1f", totalLakhs)} Lakhs",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = PureWhite
          )
        }

        Column(horizontalAlignment = Alignment.End) {
          Text(
            text = "Rate / Sq. Ft.",
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.sp,
            color = MutedGrey
          )
          val ratePerSqFt = (totalLakhs * 100000 / sqFt).toInt()
          Text(
            text = "₹$ratePerSqFt",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = GoldPrimary
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = "Includes complete architectural design, photoreal 3D renders, materials procurement, false ceiling, millwork & turnkey execution.",
        fontFamily = FontFamily.SansSerif,
        fontSize = 11.5.sp,
        color = WarmGrey,
        lineHeight = 16.sp
      )

      Spacer(modifier = Modifier.height(14.dp))
      GoldButton(
        text = "Lock Quote & Book Studio Visit",
        onClick = onBookWithEstimate,
        icon = Icons.Default.CalendarMonth,
        modifier = Modifier.fillMaxWidth()
      )
    }
  }
}

@Composable
private fun MilestoneBreakdownPreview(totalLakhs: Double) {
  LuxuryCard(hasGoldAccent = false) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text = "ESTIMATED MILESTONE CASHFLOW",
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        letterSpacing = 1.sp,
        color = GoldLight
      )
      Spacer(modifier = Modifier.height(10.dp))

      val milestones = listOf(
        Triple("Phase 1: Concept & 3D Signoff (30%)", totalLakhs * 0.30, "Booking"),
        Triple("Phase 2: Stone Quarry & Civil (40%)", totalLakhs * 0.40, "Procurement"),
        Triple("Phase 3: Millwork & Automation (20%)", totalLakhs * 0.20, "Joinery"),
        Triple("Phase 4: Styling & Handover (10%)", totalLakhs * 0.10, "Handover")
      )

      milestones.forEach { (title, amt, tag) ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = title,
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.5.sp,
            color = OffWhite
          )
          Text(
            text = "₹${String.format("%.1f", amt)} L",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 12.5.sp,
            color = GoldLight
          )
        }
      }
    }
  }
}
