package edu.bloomu.bipolarsymptomtracker.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.ui.Analysis
import edu.bloomu.bipolarsymptomtracker.ui.Entries
import edu.bloomu.bipolarsymptomtracker.ui.EntryScreen
import edu.bloomu.bipolarsymptomtracker.ui.SettingsScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Analysis.route,
    viewModel: EntryViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Analysis.route) { Analysis() }
        composable(NavigationItem.EntryScreen.route) { EntryScreen(viewModel) }
        composable(NavigationItem.Entries.route) { Entries(viewModel) }
        composable(NavigationItem.Settings.route) { SettingsScreen() }
    }
}