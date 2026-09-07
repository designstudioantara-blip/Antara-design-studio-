package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.UserRole
import com.example.ui.theme.AmberPending
import com.example.ui.theme.BlueInfo
import com.example.ui.theme.CharcoalBorder
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalElevated
import com.example.ui.theme.CharcoalSurface
import com.example.ui.theme.CrimsonAlert
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

/**
 * Signature Antara Monogram 'A' emblem with faceted gold bevels and architectural stroke
 */
@Composable
fun AntaraEmblem(
  modifier: Modifier = Modifier,
  size: Dp = 38.dp
) {
  Box(
    modifier = modifier
      .size(size)
      .clip(RoundedCornerShape(size * 0.2f))
      .background(
        Brush.linearGradient(
          listOf(Color(0xFF1E2129), Color(0xFF0F1116))
        )
      )
      .border(
        width = 1.dp,
        brush = Brush.linearGradient(
          listOf(GoldLight, GoldPrimary, GoldDark)
        ),
        shape = RoundedCornerShape(size * 0.2f)
      ),
    contentAlignment = Alignment.Center
  ) {
    Canvas(modifier = Modifier.size(size * 0.72f)) {
      val w = this.size.width
      val h = this.size.height

      // Left thin architectural stem
      drawLine(
        color = Color(0xFFF3E5AB),
        start = Offset(w * 0.18f, h * 0.92f),
        end = Offset(w * 0.46f, h * 0.08f),
        strokeWidth = w * 0.07f,
        cap = StrokeCap.Round
      )

      // Right faceted gold solid leg
      val rightLegPath = Path().apply {
        moveTo(w * 0.46f, h * 0.08f)
        lineTo(w * 0.76f, h * 0.88f)
        lineTo(w * 0.62f, h * 0.88f)
        lineTo(w * 0.46f, h * 0.32f)
        close()
      }
      drawPath(
        path = rightLegPath,
        brush = Brush.linearGradient(
          colors = listOf(Color(0xFFFFEEB2), Color(0xFFD4AF37), Color(0xFF997D2B)),
          start = Offset(w * 0.46f, 0f),
          end = Offset(w * 0.76f, h)
        )
      )

      // Crossbar extending rightward
      drawLine(
        brush = Brush.horizontalGradient(
          listOf(Color(0xFFD4AF37), Color(0xFFF3E5AB), Color(0xFFC59B27))
        ),
        start = Offset(w * 0.30f, h * 0.70f),
        end = Offset(w * 0.95f, h * 0.70f),
        strokeWidth = w * 0.06f,
        cap = StrokeCap.Round
      )
    }
  }
}

/**
 * Full Brand Banner featuring the exact Antara Design Studio emblem, cursive script,
 * serif design studio typography, and architectural baseline.
 */
@Composable
fun AntaraBrandBanner(
  modifier: Modifier = Modifier,
  compact: Boolean = false,
  showTagline: Boolean = true
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .background(
        Brush.verticalGradient(
          listOf(Color(0xFF14161C), Color(0xFF0D0E12), Color(0xFF181A22))
        )
      )
      .border(
        width = 1.dp,
        brush = Brush.linearGradient(
          listOf(Color(0xFF4A412A), Color(0xFF8E703B), Color(0xFF2A2820))
        ),
        shape = RoundedCornerShape(14.dp)
      )
      .padding(if (compact) 12.dp else 18.dp)
  ) {
    // Subtle architectural corner lines
    Canvas(modifier = Modifier.matchParentSize()) {
      val w = size.width
      val h = size.height

      // Top-left gold accent stripes
      drawLine(
        color = Color(0x33D4AF37),
        start = Offset(0f, 24.dp.toPx()),
        end = Offset(36.dp.toPx(), 0f),
        strokeWidth = 1.5f
      )
      drawLine(
        color = Color(0x1AD4AF37),
        start = Offset(0f, 36.dp.toPx()),
        end = Offset(54.dp.toPx(), 0f),
        strokeWidth = 1f
      )

      // Bottom-right gold accent stripes
      drawLine(
        color = Color(0x33D4AF37),
        start = Offset(w - 36.dp.toPx(), h),
        end = Offset(w, h - 24.dp.toPx()),
        strokeWidth = 1.5f
      )
      drawLine(
        color = Color(0x1AD4AF37),
        start = Offset(w - 54.dp.toPx(), h),
        end = Offset(w, h - 36.dp.toPx()),
        strokeWidth = 1f
      )
    }

    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        AntaraEmblem(size = if (compact) 38.dp else 52.dp)

        Spacer(modifier = Modifier.width(14.dp))

        Column {
          // Script "antara" brand text
          Text(
            text = "antara",
            fontFamily = FontFamily.Cursive,
            fontStyle = FontStyle.Italic,
            fontSize = if (compact) 22.sp else 30.sp,
            color = Color(0xFFF3E5AB),
            letterSpacing = 1.sp
          )

          // Gold horizontal rule
          Box(
            modifier = Modifier
              .width(if (compact) 140.dp else 190.dp)
              .height(1.5.dp)
              .background(
                Brush.horizontalGradient(
                  listOf(GoldLight, GoldPrimary, GoldDark)
                )
              )
          )

          Spacer(modifier = Modifier.height(3.dp))

          // Serif "design studio"
          Text(
            text = "design studio",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Medium,
            fontSize = if (compact) 12.sp else 16.sp,
            letterSpacing = 3.sp,
            color = Color(0xFFE8D39E)
          )
        }
      }

      if (showTagline) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Text(
            text = "Interior",
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.5.sp,
            letterSpacing = 1.sp,
            color = Color(0xFFC7B280)
          )
          Text(
            text = "  |  ",
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.5.sp,
            color = Color(0xFF6B5D3D)
          )
          Text(
            text = "Architecture",
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.5.sp,
            letterSpacing = 1.sp,
            color = Color(0xFFC7B280)
          )
          Text(
            text = "  |  ",
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.5.sp,
            color = Color(0xFF6B5D3D)
          )
          Text(
            text = "Landscape",
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.5.sp,
            letterSpacing = 1.sp,
            color = Color(0xFFC7B280)
          )
        }
      }
    }
  }
}

@Composable
fun AntaraTopBar(
  currentRole: UserRole,
  onRoleChanged: (UserRole) -> Unit,
  onContactClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  var menuExpanded by remember { mutableStateOf(false) }

  Surface(
    color = CharcoalDark,
    modifier = modifier.fillMaxWidth(),
    border = BorderStroke(0.5.dp, CharcoalBorder)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Brand Monogram + Title
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.testTag("brand_header")
      ) {
        AntaraEmblem(size = 36.dp)

        Spacer(modifier = Modifier.width(10.dp))
        Column {
          Text(
            text = "antara",
            fontFamily = FontFamily.Cursive,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            color = Color(0xFFF3E5AB),
            letterSpacing = 0.5.sp
          )
          Box(
            modifier = Modifier
              .width(90.dp)
              .height(1.dp)
              .background(
                Brush.horizontalGradient(listOf(GoldLight, GoldPrimary, GoldDark))
              )
          )
          Text(
            text = "DESIGN STUDIO",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Medium,
            fontSize = 8.sp,
            letterSpacing = 1.5.sp,
            color = Color(0xFFE8D39E)
          )
        }
      }

      // Right: Role Switcher Capsule + Call CTA
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box {
          Surface(
            shape = RoundedCornerShape(20.dp),
            color = CharcoalCard,
            border = BorderStroke(0.8.dp, GoldMuted),
            modifier = Modifier
              .testTag("role_switcher_button")
              .clickable { menuExpanded = true }
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .size(7.dp)
                  .clip(CircleShape)
                  .background(if (currentRole == UserRole.CLIENT) EmeraldSuccess else GoldPrimary)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = currentRole.badge,
                fontFamily = FontFamily.SansSerif,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = PureWhite
              )
              Spacer(modifier = Modifier.width(4.dp))
              Icon(
                imageVector = Icons.Default.ExpandMore,
                contentDescription = "Switch Role",
                tint = GoldLight,
                modifier = Modifier.size(14.dp)
              )
            }
          }

          DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false },
            modifier = Modifier.background(CharcoalElevated)
          ) {
            UserRole.values().forEach { role ->
              DropdownMenuItem(
                text = {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                      text = role.label,
                      fontFamily = FontFamily.SansSerif,
                      color = if (role == currentRole) GoldPrimary else PureWhite,
                      fontWeight = if (role == currentRole) FontWeight.Bold else FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (role == currentRole) {
                      Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = GoldPrimary,
                        modifier = Modifier.size(16.dp)
                      )
                    }
                  }
                },
                onClick = {
                  onRoleChanged(role)
                  menuExpanded = false
                }
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun GoldButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  icon: ImageVector? = null,
  enabled: Boolean = true
) {
  Button(
    onClick = onClick,
    enabled = enabled,
    modifier = modifier
      .height(48.dp)
      .testTag("gold_button_${text.lowercase().replace(" ", "_")}"),
    shape = RoundedCornerShape(8.dp),
    colors = ButtonDefaults.buttonColors(
      containerColor = GoldPrimary,
      contentColor = CharcoalDark,
      disabledContainerColor = CharcoalElevated,
      disabledContentColor = DarkGrey
    ),
    border = BorderStroke(1.dp, GoldLight)
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      if (icon != null) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = CharcoalDark,
          modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
      }
      Text(
        text = text,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 13.5.sp,
        letterSpacing = 0.8.sp,
        color = CharcoalDark
      )
    }
  }
}

@Composable
fun GoldOutlinedButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  icon: ImageVector? = null,
  enabled: Boolean = true
) {
  OutlinedButton(
    onClick = onClick,
    enabled = enabled,
    modifier = modifier
      .height(48.dp)
      .testTag("gold_outlined_button_${text.lowercase().replace(" ", "_")}"),
    shape = RoundedCornerShape(8.dp),
    border = BorderStroke(1.dp, GoldPrimary),
    colors = ButtonDefaults.outlinedButtonColors(
      contentColor = GoldLight
    )
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      if (icon != null) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = GoldLight,
          modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
      }
      Text(
        text = text,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        letterSpacing = 0.5.sp,
        color = GoldLight
      )
    }
  }
}

@Composable
fun LuxuryCard(
  modifier: Modifier = Modifier,
  hasGoldAccent: Boolean = false,
  onClick: (() -> Unit)? = null,
  content: @Composable () -> Unit
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .then(
        if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
      ),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = CharcoalCard),
    border = BorderStroke(
      0.8.dp,
      if (hasGoldAccent) GoldPrimary else CharcoalBorder
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    content()
  }
}

@Composable
fun LuxurySectionHeader(
  title: String,
  subtitle: String? = null,
  badgeText: String? = null,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier.fillMaxWidth()) {
    if (badgeText != null) {
      Surface(
        color = CharcoalElevated,
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(0.5.dp, GoldMuted),
        modifier = Modifier.padding(bottom = 6.dp)
      ) {
        Text(
          text = badgeText.uppercase(),
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          fontSize = 9.sp,
          letterSpacing = 1.5.sp,
          color = GoldLight,
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
      }
    }
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(
        text = title,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.3.sp,
        color = PureWhite
      )
      Spacer(modifier = Modifier.width(12.dp))
      Box(
        modifier = Modifier
          .weight(1f)
          .height(1.dp)
          .background(
            Brush.horizontalGradient(
              listOf(GoldMuted, Color.Transparent)
            )
          )
      )
    }
    if (subtitle != null) {
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = subtitle,
        fontFamily = FontFamily.SansSerif,
        fontSize = 12.5.sp,
        color = MutedGrey,
        lineHeight = 18.sp
      )
    }
  }
}

@Composable
fun StatusBadge(
  text: String,
  type: StatusBadgeType = StatusBadgeType.GOLD,
  modifier: Modifier = Modifier
) {
  val (bgColor, textColor, borderColor) = when (type) {
    StatusBadgeType.SUCCESS -> Triple(Color(0x1F4E9F76), EmeraldSuccess, EmeraldSuccess)
    StatusBadgeType.PENDING -> Triple(Color(0x1FD98A36), AmberPending, AmberPending)
    StatusBadgeType.INFO -> Triple(Color(0x1F4A90E2), BlueInfo, BlueInfo)
    StatusBadgeType.ALERT -> Triple(Color(0x1FD9534F), CrimsonAlert, CrimsonAlert)
    StatusBadgeType.GOLD -> Triple(Color(0x1FC19A5B), GoldLight, GoldPrimary)
  }

  Surface(
    shape = RoundedCornerShape(12.dp),
    color = bgColor,
    border = BorderStroke(0.6.dp, borderColor),
    modifier = modifier
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
      Box(
        modifier = Modifier
          .size(5.dp)
          .clip(CircleShape)
          .background(textColor)
      )
      Spacer(modifier = Modifier.width(5.dp))
      Text(
        text = text,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.5.sp,
        color = textColor
      )
    }
  }
}

enum class StatusBadgeType {
  SUCCESS, PENDING, INFO, ALERT, GOLD
}

@Composable
fun LinearProgressWithGold(
  progress: Float,
  modifier: Modifier = Modifier
) {
  val animatedProgress by animateFloatAsState(targetValue = progress, label = "progress")
  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(6.dp)
      .clip(RoundedCornerShape(3.dp))
      .background(CharcoalElevated)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth(animatedProgress)
        .height(6.dp)
        .clip(RoundedCornerShape(3.dp))
        .background(
          Brush.horizontalGradient(
            listOf(GoldGradientEnd, GoldGradientStart)
          )
        )
    )
  }
}

@Composable
fun BeforeAfterVisualizer(
  beforeDescription: String,
  afterDescription: String,
  modifier: Modifier = Modifier
) {
  var showAfter by remember { mutableStateOf(true) }

  LuxuryCard(
    modifier = modifier,
    hasGoldAccent = true
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.CompareArrows,
            contentDescription = null,
            tint = GoldPrimary,
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = if (showAfter) "AFTER TRANSFORMATION" else "BEFORE (RAW SHELL)",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            color = if (showAfter) GoldLight else MutedGrey
          )
        }

        Surface(
          shape = RoundedCornerShape(16.dp),
          color = CharcoalElevated,
          border = BorderStroke(0.6.dp, GoldMuted),
          modifier = Modifier
            .clickable { showAfter = !showAfter }
            .testTag("toggle_before_after")
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Default.SwapHoriz,
              contentDescription = "Switch view",
              tint = GoldLight,
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = if (showAfter) "View Before" else "View After",
              fontFamily = FontFamily.SansSerif,
              fontSize = 10.5.sp,
              color = PureWhite
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(
            if (showAfter) CharcoalSurface else Color(0xFF141717)
          )
          .border(
            0.5.dp,
            if (showAfter) GoldMuted else CharcoalBorder,
            RoundedCornerShape(8.dp)
          )
          .padding(14.dp)
      ) {
        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(if (showAfter) EmeraldSuccess else AmberPending)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = if (showAfter) "Handover Masterpiece" else "Initial Site Condition",
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.SemiBold,
              fontSize = 13.sp,
              color = PureWhite
            )
          }
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = if (showAfter) afterDescription else beforeDescription,
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            color = WarmGrey,
            lineHeight = 18.sp
          )
        }
      }
    }
  }
}
