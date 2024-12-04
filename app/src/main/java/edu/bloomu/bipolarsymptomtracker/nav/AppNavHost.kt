package edu.bloomu.bipolarsymptomtracker.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.ui.Analysis
import edu.bloomu.bipolarsymptomtracker.ui.Entries
import edu.bloomu.bipolarsymptomtracker.ui.EntryScreen
import edu.bloomu.bipolarsymptomtracker.ui.InitialSetup
import edu.bloomu.bipolarsymptomtracker.ui.SettingsScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Welcome.route,
    viewModel: EntryViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Analysis.route) { Analysis(viewModel) }
        composable(NavigationItem.Entries.route) { Entries(viewModel, navController) }
        composable(NavigationItem.Settings.route) { SettingsScreen() }
        composable(NavigationItem.Welcome.route) { InitialSetup() }
        composable(
            route = NavigationItem.EntryScreen.route + "/{entryId}",
            arguments = listOf(navArgument("entryId") { type = NavType.IntType } )
        ) { backStackEntry ->
            EntryScreen(
                viewModel = viewModel,
                entryId = backStackEntry.arguments?.getInt("entryId") ?: 0
            )
        }
    }
}