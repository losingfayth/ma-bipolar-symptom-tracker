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

/**
 * Handles the navigation between app screens
 *
 * @param modifier Formatting
 * @param navController Pre-initialized navigation controller
 * @param startDestination Where to go when the app starts up
 * @param viewModel Manages Room database
 * @param onValueChange Function to update screen title in scaffold
 * @param onFabChange Function to update floating action button in scaffold
 * @param onSetupCompleted Function to allow Welcome screen to be closed
 */
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Welcome.route,
    viewModel: EntryViewModel,
    onValueChange: (String) -> Unit,
    onFabChange: (fab: @Composable () -> Unit) -> Unit,
    onSetupCompleted: () -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        // Go to Analysis screen
        composable(NavigationItem.Analysis.route) {
            Analysis(
                viewModel = viewModel,
                navController = navController,
                onFabChange = onFabChange
            );
            onValueChange(NavigationItem.Analysis.toString())
        }

        // Go to Entries screen
        composable(NavigationItem.Entries.route) {
            Entries(
                viewModel = viewModel,
                navController = navController,
                onFabChange = onFabChange
            );
            onValueChange(NavigationItem.Entries.toString())
        }

        // Go to Settings screen
        composable(NavigationItem.Settings.route) {
            SettingsScreen(
                onFabChange = onFabChange,
                viewModel = viewModel,
                onClick = {}
            );
            onValueChange(NavigationItem.Settings.toString())
        }

        // Go to Welcome screen
        composable(NavigationItem.Welcome.route) {
            InitialSetup(
                onClick = onSetupCompleted,
                navController = navController
            );
            onValueChange(NavigationItem.Welcome.toString()) }

        // Go to Edit Entry screen
        composable(
            route = NavigationItem.EditEntry.route + "/{entryId}",
            arguments = listOf(navArgument("entryId") { type = NavType.IntType } )
        ) { backStackEntry ->
            EntryScreen(
                viewModel = viewModel,
                entryId = backStackEntry.arguments?.getInt("entryId") ?: 0,
                onFabChange = onFabChange,
                navController = navController
            )
            onValueChange(NavigationItem.EditEntry.toString())
        }
    }
}