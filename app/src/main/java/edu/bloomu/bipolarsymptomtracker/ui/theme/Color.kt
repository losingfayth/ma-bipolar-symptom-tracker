package edu.bloomu.bipolarsymptomtracker.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardColors
import androidx.compose.material3.IconButtonColors
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Primary colors
val md_theme_light_primary = Color(0xFF6750A4)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFEADDFF)
val md_theme_light_onPrimaryContainer = Color(0xFF21005D)

// Secondary colors
val md_theme_light_secondary = Color(0xFF625B71)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFD1C4E9)
val md_theme_light_onSecondaryContainer = Color(0xFF1D192B)

// Tertiary colors
val md_theme_light_tertiary = Color(0xFF7D5260)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFFFD8E4)
val md_theme_light_onTertiaryContainer = Color(0xFF31111D)

// Error colors
val md_theme_light_error = Color(0xFFB3261E)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_errorContainer = Color(0xFFF9DEDC)
val md_theme_light_onErrorContainer = Color(0xFF410E0B)

// Background colors
val md_theme_light_background = Color(0xFFFFFBFE)
val md_theme_light_onBackground = Color(0xFF1C1B1F)

// Surface colors
val md_theme_light_surface = Color(0xFFFFFBFE)
val md_theme_light_onSurface = Color(0xFF1C1B1F)
val md_theme_light_surfaceVariant = Color(0xFFE7E0EC)
val md_theme_light_onSurfaceVariant = Color(0xFF49454F)

// Outline colors
val md_theme_light_outline = Color(0xFF79747E)

// Inverse colors
val md_theme_light_inverseOnSurface = Color(0xFFF4EFF4)
val md_theme_light_inverseSurface = Color(0xFF313033)
val md_theme_light_inversePrimary = Color(0xFFD0BCFF)

// Custom Neutral and Custom Accent colors
val md_theme_light_neutral = Color(0xFFB0BEC5)
val md_theme_light_accent = Color(0xFF64B5F6)

// Emotional State Highlight Colors
val md_theme_light_state_manic = Color(0xFFF51744)
val md_theme_light_state_hypo_manic = Color(0xFFF15074)
val md_theme_light_state_depressed = Color(0xFF455DFC)
val md_theme_light_state_hypo_depressed = Color(0xFF6678FA)
val md_theme_light_state_unstable = Color(0xFF749436)
val md_theme_light_state_none = Color(0xFF9E9E9E)

val md_theme_light_button_error = ButtonColors(
    containerColor = md_theme_light_error,
    contentColor = md_theme_light_onError,
    disabledContainerColor = md_theme_light_errorContainer,
    disabledContentColor = md_theme_light_onErrorContainer
)

val md_theme_light_button_error_pressed = ButtonColors(
    contentColor = md_theme_light_error,
    containerColor = md_theme_light_onError,
    disabledContainerColor = md_theme_light_errorContainer,
    disabledContentColor = md_theme_light_onErrorContainer
)

val md_theme_light_button_primary = ButtonColors(
    containerColor = md_theme_light_primaryContainer,
    contentColor = md_theme_light_onPrimaryContainer,
    disabledContainerColor = md_theme_light_errorContainer,
    disabledContentColor = md_theme_light_onErrorContainer
)

val md_theme_light_button_primary_pressed = ButtonColors(
    contentColor = md_theme_light_onPrimaryContainer,
    containerColor = md_theme_light_primaryContainer,
    disabledContainerColor = md_theme_light_onErrorContainer,
    disabledContentColor = md_theme_light_errorContainer
)

val md_theme_light_button_secondary = ButtonColors(
    containerColor = md_theme_light_secondaryContainer,
    contentColor = md_theme_light_onSecondaryContainer,
    disabledContainerColor = md_theme_light_errorContainer,
    disabledContentColor = md_theme_light_onErrorContainer
)

val md_theme_light_button_secondary_pressed = ButtonColors(
    contentColor = md_theme_light_secondaryContainer,
    containerColor = md_theme_light_onSecondaryContainer,
    disabledContainerColor = md_theme_light_onErrorContainer,
    disabledContentColor = md_theme_light_errorContainer
)

val md_theme_light_card_dark = CardColors(
    contentColor = md_theme_light_primaryContainer,
    containerColor = md_theme_light_onPrimaryContainer,
    disabledContainerColor = md_theme_light_onErrorContainer,
    disabledContentColor = md_theme_light_errorContainer
)

val md_theme_light_card_light = CardColors(
    contentColor = md_theme_light_onPrimaryContainer,
    containerColor = md_theme_light_primaryContainer,
    disabledContainerColor = md_theme_light_onErrorContainer,
    disabledContentColor = md_theme_light_errorContainer
)

val md_theme_light_card_alt_light = CardColors(
    contentColor = md_theme_light_onPrimary,
    containerColor = md_theme_light_primary,
    disabledContainerColor = md_theme_light_onErrorContainer,
    disabledContentColor = md_theme_light_errorContainer
)

val md_theme_light_card_alt_dark = CardColors(
    containerColor = md_theme_light_onPrimary,
    contentColor = md_theme_light_primary,
    disabledContainerColor = md_theme_light_onErrorContainer,
    disabledContentColor = md_theme_light_errorContainer
)

val md_theme_light_icon_button_light = IconButtonColors(
    containerColor = md_theme_light_secondaryContainer,
    contentColor = md_theme_light_onSecondaryContainer,
    disabledContainerColor = md_theme_light_errorContainer,
    disabledContentColor = md_theme_light_onErrorContainer
)

val md_theme_light_icon_button_dark = IconButtonColors(
    contentColor = md_theme_light_secondaryContainer,
    containerColor = md_theme_light_onSecondaryContainer,
    disabledContainerColor = md_theme_light_onErrorContainer,
    disabledContentColor = md_theme_light_errorContainer
)