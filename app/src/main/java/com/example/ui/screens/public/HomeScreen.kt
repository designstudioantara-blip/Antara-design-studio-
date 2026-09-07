package com.example.ui.screens.public

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Weekend
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.PortfolioItemEntity
import com.example.ui.components.AntaraBrandBanner
import com.example.ui.components.AntaraEmblem
import com.example.ui.components.BeforeAfterVisualizer
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
import com.example.ui.theme.DarkGrey
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldDark
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
fun HomeScreen(
  viewModel: MainViewModel,
  onNavigateToBooking: () -> Unit,
  onNavigateToPortfolio: () -> Unit,
  onNavigateToEstimator: () -> Unit,
  onNavigateToStyleQuiz: () -> Unit,
  onNavigateToServices: () -> Unit,
  onNavigateToContact: () -> Unit,
  modifier: Modifier = Modifier
) {
  val portfolioItems by viewModel.portfolioList.collectAsStateWithLifecycle()
  val featuredItem = portfolioItems.firstOrNull { it.isFeatured } ?: portfolioItems.firstOrNull()
  val context = LocalContext.current

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(CharcoalDark)
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    // Top spacing
    item { Spacer(modifier = Modifier.height(6.dp)) }

    // Hero Luxury Banner
    item {
      LuxuryHeroBanner(
        onBookClick = onNavigateToBooking,
        onEstimatorClick = onNavigateToEstimator
      )
    }

    // Studio Pillars / Quick Interactive Discovery Tools
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        QuickToolCard(
          title = "Cost Estimator",
          subtitle = "Dynamic budget planner",
          icon = Icons.Default.Calculate,
          onClick = onNavigateToEstimator,
          modifier = Modifier.weight(1f)
        )
        QuickToolCard(
          title = "Style Matcher",
          subtitle = "Find your aesthetic",
          icon = Icons.Default.Psychology,
          onClick = onNavigateToStyleQuiz,
          modifier = Modifier.weight(1f)
        )
      }
    }

    // Studio Achievements Stats Bar
    item {
      LuxuryStatsStrip()
    }

    // Core Services Overview
    item {
      Column {
        LuxurySectionHeader(
          badgeText = "Comprehensive Solutions",
          title = "Turnkey Design Services",
          subtitle = "From spatial architecture to bespoke joinery and interior styling"
        )
        Spacer(modifier = Modifier.height(14.dp))
        ServicesHorizontalShowcase(onExploreServices = onNavigateToServices)
      }
    }

    // Featured Project Spotlight (Before / After)
    item {
      if (featuredItem != null) {
        Column {
          LuxurySectionHeader(
            badgeText = "Featured Masterpiece",
            title = featuredItem.title,
            subtitle = "${featuredItem.location} • ${featuredItem.areaSqFt} Sq. Ft. • ${featuredItem.style}"
          )
          Spacer(modifier = Modifier.height(12.dp))
          BeforeAfterVisualizer(
            beforeDescription = featuredItem.beforeImageDesc,
            afterDescription = featuredItem.afterImageDesc
          )
          Spacer(modifier = Modifier.height(10.dp))
          GoldOutlinedButton(
            text = "Explore Full Portfolio Gallery",
            onClick = onNavigateToPortfolio,
            icon = Icons.Default.ArrowForward,
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
    }

    // Client Testimonials / Accolades
    item {
      ClientTestimonialsSection()
    }

    // Surat Studio Location & Direct Booking CTA
    item {
      SuratStudioLocationCard(
        onBookAppointment = onNavigateToBooking,
        onContactUs = onNavigateToContact
      )
    }

    // Bottom padding
    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}

@Composable
private fun LuxuryHeroBanner(
  onBookClick: () -> Unit,
  onEstimatorClick: () -> Unit
) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // Official Logo Brand Banner
    AntaraBrandBanner(
      showTagline = true,
      compact = false
    )

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(
          Brush.verticalGradient(
            listOf(CharcoalElevated, CharcoalCard, CharcoalSurface)
          )
        )
        .border(1.dp, GoldMuted, RoundedCornerShape(16.dp))
        .padding(20.dp)
    ) {
      Column {
        // Monogram tag
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.fillMaxWidth()
        ) {
          Surface(
            color = CharcoalDark,
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(0.6.dp, GoldPrimary)
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = GoldLight,
                modifier = Modifier.size(12.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "PREMIER SURAT STUDIO",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 9.5.sp,
                letterSpacing = 1.2.sp,
                color = GoldLight
              )
            }
          }

          StatusBadge(text = "Turnkey Luxury", type = StatusBadgeType.GOLD)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = "Blending creativity &\nfunctionality in every design.",
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          fontSize = 22.sp,
          lineHeight = 30.sp,
          color = PureWhite
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "Antara Design Studio creates bespoke residential mansions, penthouses, and commercial spaces with turnkey execution and artisanal precision.",
          fontFamily = FontFamily.SansSerif,
          fontSize = 13.sp,
          lineHeight = 19.sp,
          color = WarmGrey
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          GoldButton(
            text = "Book Consultation",
            onClick = onBookClick,
            icon = Icons.Default.CalendarMonth,
            modifier = Modifier.weight(1.1f)
          )
          GoldOutlinedButton(
            text = "Cost Calculator",
            onClick = onEstimatorClick,
            icon = Icons.Default.Calculate,
            modifier = Modifier.weight(0.9f)
          )
        }
      }
    }
  }
}

@Composable
private fun QuickToolCard(
  title: String,
  subtitle: String,
  icon: ImageVector,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  LuxuryCard(
    modifier = modifier,
    onClick = onClick,
    hasGoldAccent = true
  ) {
    Row(
      modifier = Modifier.padding(14.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(38.dp)
          .clip(RoundedCornerShape(8.dp))
          .background(CharcoalElevated)
          .border(0.6.dp, GoldPrimary, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = GoldLight,
          modifier = Modifier.size(20.dp)
        )
      }
      Spacer(modifier = Modifier.width(10.dp))
      Column {
        Text(
          text = title,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          fontSize = 13.sp,
          color = PureWhite
        )
        Text(
          text = subtitle,
          fontFamily = FontFamily.SansSerif,
          fontSize = 10.5.sp,
          color = MutedGrey
        )
      }
    }
  }
}

@Composable
private fun LuxuryStatsStrip() {
  Surface(
    color = CharcoalSurface,
    shape = RoundedCornerShape(12.dp),
    border = BorderStroke(0.6.dp, CharcoalBorder),
    modifier = Modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 14.dp, horizontal = 8.dp),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically
    ) {
      StatItem("120+", "Turnkey Projects")
      Box(modifier = Modifier.width(1.dp).height(28.dp).background(CharcoalBorder))
      StatItem("15+", "Design Awards")
      Box(modifier = Modifier.width(1.dp).height(28.dp).background(CharcoalBorder))
      StatItem("98%", "Client Retention")
      Box(modifier = Modifier.width(1.dp).height(28.dp).background(CharcoalBorder))
      StatItem("8+ Yrs", "Surat Excellence")
    }
  }
}

@Composable
private fun StatItem(value: String, label: String) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Text(
      text = value,
      fontFamily = FontFamily.Serif,
      fontWeight = FontWeight.Bold,
      fontSize = 16.sp,
      color = GoldPrimary
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
      text = label,
      fontFamily = FontFamily.SansSerif,
      fontSize = 9.5.sp,
      color = MutedGrey,
      textAlign = TextAlign.Center
    )
  }
}

@Composable
private fun ServicesHorizontalShowcase(onExploreServices: () -> Unit) {
  val services = listOf(
    Triple("Turnkey Residential", "End-to-end luxury interiors with single-point warranty & zero hassle.", Icons.Default.Home),
    Triple("Commercial & Corporate", "Executive offices, corporate boardrooms & luxury boutique retail.", Icons.Default.Architecture),
    Triple("Bespoke Joinery", "Custom hand-crafted millwork, veneers, and motorized wardrobes.", Icons.Default.Weekend),
    Triple("Landscape & Biophilic", "Indoor vertical gardens, courtyards, and terrace zen lounges.", Icons.Default.Park)
  )

  Column {
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      items(services) { (title, desc, icon) ->
        Box(
          modifier = Modifier
            .width(220.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CharcoalCard)
            .border(0.6.dp, CharcoalBorder, RoundedCornerShape(12.dp))
            .clickable { onExploreServices() }
            .padding(14.dp)
        ) {
          Column {
            Box(
              modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(CharcoalElevated)
                .border(0.5.dp, GoldPrimary, CircleShape),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = icon,
                contentDescription = null,
                tint = GoldLight,
                modifier = Modifier.size(18.dp)
              )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
              text = title,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              fontSize = 14.sp,
              color = PureWhite
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = desc,
              fontFamily = FontFamily.SansSerif,
              fontSize = 11.5.sp,
              color = MutedGrey,
              lineHeight = 16.sp,
              maxLines = 3
            )
          }
        }
      }
    }
  }
}

@Composable
private fun ClientTestimonialsSection() {
  Column {
    LuxurySectionHeader(
      badgeText = "Client Trust",
      title = "Words from Patrons",
      subtitle = "Surat's leading business leaders and families on their Antara experience"
    )
    Spacer(modifier = Modifier.height(12.dp))

    LuxuryCard(hasGoldAccent = false) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          repeat(5) {
            Icon(
              imageVector = Icons.Default.Star,
              contentDescription = null,
              tint = GoldPrimary,
              modifier = Modifier.size(16.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "5.0 Rating • Althan Penthouse",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            color = GoldLight
          )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = "\"Antara transformed our 4800 sq ft penthouse into an architectural masterpiece. The precision in bookmatched marble alignment and bespoke veneer millwork exceeded all our expectations. Turnkey handover was right on schedule!\"",
          fontFamily = FontFamily.Serif,
          fontSize = 12.5.sp,
          lineHeight = 19.sp,
          color = OffWhite
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = "— Rajesh & Ananya Mehta, Althan Surat",
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.SemiBold,
          fontSize = 11.5.sp,
          color = GoldLight
        )
      }
    }
  }
}

@Composable
private fun SuratStudioLocationCard(
  onBookAppointment: () -> Unit,
  onContactUs: () -> Unit
) {
  LuxuryCard(hasGoldAccent = true) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = GoldPrimary,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Experience Center & Studio",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = PureWhite
          )
        }
        StatusBadge(text = "Open Mon-Sat", type = StatusBadgeType.SUCCESS)
      }

      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = "242, Times World, Althan, Surat, Gujarat 395017, India",
        fontFamily = FontFamily.SansSerif,
        fontSize = 12.5.sp,
        color = WarmGrey
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "Direct: 09725314309 • designstudioantara@gmail.com",
        fontFamily = FontFamily.SansSerif,
        fontSize = 11.5.sp,
        color = GoldLight
      )

      Spacer(modifier = Modifier.height(14.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        GoldButton(
          text = "Visit Studio",
          onClick = onBookAppointment,
          icon = Icons.Default.CalendarMonth,
          modifier = Modifier.weight(1f)
        )
        GoldOutlinedButton(
          text = "Studio Details",
          onClick = onContactUs,
          modifier = Modifier.weight(1f)
        )
      }
    }
  }
}
