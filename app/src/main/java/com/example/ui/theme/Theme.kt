package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val AntaraDarkColorScheme = darkColorScheme(
  primary = GoldPrimary,
  onPrimary = CharcoalDark,
  primaryContainer = GoldMuted,
  onPrimaryContainer = GoldLight,
  secondary = GoldLight,
  onSecondary = CharcoalDark,
  secondaryContainer = CharcoalElevated,
  onSecondaryContainer = WarmGrey,
  tertiary = AmberPending,
  onTertiary = PureWhite,
  background = CharcoalDark,
  onBackground = PureWhite,
  surface = CharcoalSurface,
  onSurface = PureWhite,
  surfaceVariant = CharcoalCard,
  onSurfaceVariant = WarmGrey,
  outline = CharcoalBorder,
  outlineVariant = CharcoalElevated,
  error = CrimsonAlert,
  onError = PureWhite
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = true, // Moody sophistication luxury dark theme by default
  content: @Composable () -> Unit
) {
  val colorScheme = AntaraDarkColorScheme
  val view = LocalView.current

  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = CharcoalDark.toArgb()
      window.navigationBarColor = CharcoalDark.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
      WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
