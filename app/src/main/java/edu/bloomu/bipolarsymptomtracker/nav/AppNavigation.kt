package edu.bloomu.bipolarsymptomtracker.nav

enum class Screen {
    ANALYSIS,
    ENTRY_SCREEN,
    ENTRIES,
    SETTINGS,
    WELCOME
}

sealed class NavigationItem(
    val route: String
) {
    object Analysis : NavigationItem(Screen.ANALYSIS.name)
    object EntryScreen : NavigationItem(Screen.ENTRY_SCREEN.name)
    object Entries : NavigationItem(Screen.ENTRIES.name)
    object Settings : NavigationItem(Screen.SETTINGS.name)
    object Welcome : NavigationItem(Screen.WELCOME.name)
}