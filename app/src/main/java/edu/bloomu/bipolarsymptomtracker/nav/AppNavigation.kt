package edu.bloomu.bipolarsymptomtracker.nav

import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings

/**
 * All the Screens in the app
 */
enum class Screen {
    ANALYSIS,
    EDIT_ENTRY,
    ENTRIES,
    SETTINGS,
    WELCOME;

    /**
     * @return the scaffold title for this Screen
     */
    override fun toString(): String {
        return when(this) {
            ANALYSIS -> Strings.Screens.Analysis.screenTitle
            EDIT_ENTRY -> Strings.Screens.EditEntry.screenTitle
            ENTRIES -> Strings.Screens.Entries.screenTitle
            SETTINGS -> Strings.Screens.Settings.screenTitle
            WELCOME -> Strings.Screens.Welcome.screenTitle
        }
    }
}

/**
 * @param route The route to a screen for the nav controller
 */
sealed class NavigationItem(val route: String) {
    data object Analysis : NavigationItem(Screen.ANALYSIS.name)
    data object EditEntry : NavigationItem(Screen.EDIT_ENTRY.name)
    data object Entries : NavigationItem(Screen.ENTRIES.name)
    data object Settings : NavigationItem(Screen.SETTINGS.name)
    data object Welcome : NavigationItem(Screen.WELCOME.name)
}