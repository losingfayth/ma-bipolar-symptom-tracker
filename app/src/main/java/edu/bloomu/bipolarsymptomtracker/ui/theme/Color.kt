package edu.bloomu.bipolarsymptomtracker.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardColors
import androidx.compose.material3.IconButtonColors
import androidx.compose.ui.graphics.Color

// Primary colors
val md_theme_primary = Color(0xFF6750A4)
val md_theme_onPrimary = Color(0xFFFFFFFF)
val md_theme_primaryContainer = Color(0xFFEADDFF)
val md_theme_onPrimaryContainer = Color(0xFF21005D)

// Secondary colors
val md_theme_secondary = Color(0xFF625B71)
val md_theme_onSecondary = Color(0xFFFFFFFF)
val md_theme_secondaryContainer = Color(0xFFD1C4E9)
val md_theme_onSecondaryContainer = Color(0xFF1D192B)

// Tertiary colors
val md_theme_tertiary = Color(0xFF7D5260)
val md_theme_onTertiary = Color(0xFFFFFFFF)
val md_theme_tertiaryContainer = Color(0xFFFFD8E4)
val md_theme_onTertiaryContainer = Color(0xFF31111D)

// Error colors
val md_theme_error = Color(0xFFB3261E)
val md_theme_onError = Color(0xFFFFFFFF)
val md_theme_errorContainer = Color(0xFFF9DEDC)
val md_theme_onErrorContainer = Color(0xFF410E0B)

// Background colors
val md_theme_background = Color(0xFFFFFBFE)
val md_theme_onBackground = Color(0xFF1C1B1F)

// Surface colors
val md_theme_surface = Color(0xFFFFFBFE)
val md_theme_onSurface = Color(0xFF1C1B1F)
val md_theme_surfaceVariant = Color(0xFFE7E0EC)
val md_theme_onSurfaceVariant = Color(0xFF49454F)

// Outline colors
val md_theme_outline = Color(0xFF79747E)

// Inverse colors
val md_theme_inverseOnSurface = Color(0xFFF4EFF4)
val md_theme_inverseSurface = Color(0xFF313033)
val md_theme_inversePrimary = Color(0xFFD0BCFF)

// Custom Neutral and Custom Accent colors
val md_theme_neutral = Color(0xFFB0BEC5)
val md_theme_accent = Color(0xFF64B5F6)

// Emotional State Highlight Colors
val md_theme_state_manic = Color(0xFFF51744)
val md_theme_state_hypo_manic = Color(0xFFF15074)
val md_theme_state_depressed = Color(0xFF455DFC)
val md_theme_state_hypo_depressed = Color(0xFF6678FA)
val md_theme_state_unstable = Color(0xFF749436)
val md_theme_state_none = Color(0xFF9E9E9E)

val md_theme_disabledContainer = md_theme_neutral
val md_theme_onDisabledContainer = md_theme_outline

val md_theme_button_error = ButtonColors(
    containerColor = md_theme_error,
    contentColor = md_theme_onError,
    disabledContainerColor = md_theme_neutral,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_button_error_pressed = ButtonColors(
    contentColor = md_theme_error,
    containerColor = md_theme_onError,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_button_primary = ButtonColors(
    containerColor = md_theme_primaryContainer,
    contentColor = md_theme_onPrimaryContainer,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_button_primary_pressed = ButtonColors(
    contentColor = md_theme_onPrimaryContainer,
    containerColor = md_theme_primaryContainer,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_button_secondary = ButtonColors(
    containerColor = md_theme_secondaryContainer,
    contentColor = md_theme_onSecondaryContainer,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_button_secondary_pressed = ButtonColors(
    contentColor = md_theme_secondaryContainer,
    containerColor = md_theme_onSecondaryContainer,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_card_pressed = CardColors(
    contentColor = md_theme_primaryContainer,
    containerColor = md_theme_onPrimaryContainer,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_card = CardColors(
    contentColor = md_theme_onPrimaryContainer,
    containerColor = md_theme_primaryContainer,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_card_secondary = CardColors(
    contentColor = md_theme_onPrimary,
    containerColor = md_theme_primary,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_card_secondary_pressed = CardColors(
    containerColor = md_theme_onPrimary,
    contentColor = md_theme_primary,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_icon_button = IconButtonColors(
    containerColor = md_theme_secondaryContainer,
    contentColor = md_theme_onSecondaryContainer,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_icon_button_pressed = IconButtonColors(
    contentColor = md_theme_secondaryContainer,
    containerColor = md_theme_onSecondaryContainer,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_icon_button_tertiary = IconButtonColors(
    contentColor = md_theme_onTertiaryContainer,
    containerColor = md_theme_tertiaryContainer,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)

val md_theme_icon_button_tertiary_pressed = IconButtonColors(
    containerColor  = md_theme_onTertiaryContainer,
    contentColor = md_theme_tertiaryContainer,
    disabledContainerColor = md_theme_disabledContainer,
    disabledContentColor = md_theme_onDisabledContainer
)