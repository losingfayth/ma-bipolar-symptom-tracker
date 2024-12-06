package edu.bloomu.bipolarsymptomtracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme = lightColorScheme(
    primary = md_theme_primary,
    secondary = md_theme_secondary,
    tertiary = md_theme_tertiary,
    error = md_theme_error,

    onPrimary = md_theme_onPrimary,
    onSecondary = md_theme_onSecondary,
    onTertiary = md_theme_onTertiary,
    onError = md_theme_onError,

    outline = md_theme_outline,

    background = md_theme_background,
    surface = md_theme_surface,
    surfaceVariant = md_theme_surfaceVariant,
    primaryContainer = md_theme_primaryContainer,
    secondaryContainer = md_theme_secondaryContainer,
    tertiaryContainer = md_theme_tertiaryContainer,
    errorContainer = md_theme_errorContainer,

    onBackground = md_theme_onBackground,
    onSurface = md_theme_onSurface,
    onSurfaceVariant = md_theme_onSurfaceVariant,
    onPrimaryContainer = md_theme_onPrimaryContainer,
    onSecondaryContainer = md_theme_onSecondaryContainer,
    onTertiaryContainer = md_theme_onTertiaryContainer,
    onErrorContainer = md_theme_onErrorContainer,

    inversePrimary = md_theme_inversePrimary,
    inverseSurface = md_theme_inverseSurface,
    inverseOnSurface = md_theme_inverseOnSurface,
    )

@Composable
fun BipolarSymptomTrackerTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = ColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}