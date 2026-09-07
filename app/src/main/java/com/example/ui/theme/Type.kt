package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
  displayLarge = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Bold,
    fontSize = 36.sp,
    lineHeight = 44.sp,
    letterSpacing = 0.5.sp,
    color = PureWhite
  ),
  displayMedium = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Bold,
    fontSize = 30.sp,
    lineHeight = 38.sp,
    letterSpacing = 0.5.sp,
    color = PureWhite
  ),
  displaySmall = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.5.sp,
    color = PureWhite
  ),
  headlineLarge = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.SemiBold,
    fontSize = 22.sp,
    lineHeight = 30.sp,
    letterSpacing = 0.5.sp,
    color = PureWhite
  ),
  headlineMedium = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    lineHeight = 26.sp,
    letterSpacing = 0.25.sp,
    color = PureWhite
  ),
  headlineSmall = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.15.sp,
    color = PureWhite
  ),
  titleLarge = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.SemiBold,
    fontSize = 17.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.15.sp,
    color = PureWhite
  ),
  titleMedium = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.15.sp,
    color = PureWhite
  ),
  titleSmall = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Medium,
    fontSize = 13.sp,
    lineHeight = 18.sp,
    letterSpacing = 0.1.sp,
    color = GoldPrimary
  ),
  bodyLarge = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.25.sp,
    color = WarmGrey
  ),
  bodyMedium = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    lineHeight = 19.sp,
    letterSpacing = 0.2.sp,
    color = MutedGrey
  ),
  bodySmall = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.2.sp,
    color = DarkGrey
  ),
  labelLarge = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.SemiBold,
    fontSize = 13.sp,
    lineHeight = 18.sp,
    letterSpacing = 1.sp,
    color = CharcoalDark
  ),
  labelMedium = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp,
    color = GoldPrimary
  ),
  labelSmall = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Medium,
    fontSize = 10.sp,
    lineHeight = 14.sp,
    letterSpacing = 0.5.sp,
    color = MutedGrey
  )
)
