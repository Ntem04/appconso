package com.example.ecoconso.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = EcoGreenPrimaryDark,
    secondary = EcoSkyBlue,
    tertiary = EcoAmberWarning,
    background = EcoBackgroundDark,
    surface = EcoSurfaceDark,
    onPrimary = EcoSurfaceWhite,
    onSecondary = EcoSurfaceWhite,
    onTertiary = EcoSurfaceWhite,
    onBackground = EcoSurfaceWhite,
    onSurface = EcoSurfaceWhite,
    outline = Color(0xFF334155),
    surfaceVariant = Color(0xFF334155),
    onSurfaceVariant = Color(0xFF94A3B8)
)

private val LightColorScheme = lightColorScheme(
    primary = EcoGreenPrimary,
    secondary = EcoSkyBlue,
    tertiary = EcoAmberWarning,
    background = EcoBackgroundLight,
    surface = EcoSurfaceWhite,
    onPrimary = EcoSurfaceWhite,
    onSecondary = EcoSurfaceWhite,
    onTertiary = EcoSurfaceWhite,
    onBackground = EcoTextPrimary,
    onSurface = EcoTextPrimary,
    outline = EcoBorderColor,
    surfaceVariant = EcoGreenLight,
    onSurfaceVariant = EcoTextSecondary
)

@Composable
fun EcoConsoTheme(
    darkTheme: Boolean = ThemeManager.isDarkModeManual ?: isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
