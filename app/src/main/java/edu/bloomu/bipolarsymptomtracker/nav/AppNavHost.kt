package edu.bloomu.bipolarsymptomtracker.nav

import androidx.compose.foundation.layout.PaddingValues
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
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Welcome.route,
    viewModel: EntryViewModel,
    innerPadding: PaddingValues,
    onValueChange: (String) -> Unit,
    onFabChange: (fab: @Composable () -> Unit) -> Unit,
    onClick: () -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Analysis.route) {
            Analysis(
                viewModel = viewModel,
                navController = navController,
                onFabChange = onFabChange
            );
            onValueChange(Strings.ScreenTitles.Analysis)
        }
        composable(NavigationItem.Entries.route) {
            Entries(
                viewModel = viewModel,
                navController = navController,
                onFabChange = onFabChange
            );
            onValueChange(Strings.ScreenTitles.Entries)
        }
        composable(NavigationItem.Settings.route) {
            SettingsScreen(
                onFabChange = onFabChange,
                viewModel = viewModel,
                onClick = {}
            );
            onValueChange(Strings.ScreenTitles.Settings)
        }
        composable(NavigationItem.Welcome.route) {
            InitialSetup(
                onFabChange = onFabChange,
                onClick = onClick,
                navController = navController
            );
            onValueChange(Strings.ScreenTitles.Welcome) }
        composable(
            route = NavigationItem.EntryScreen.route + "/{entryId}",
            arguments = listOf(navArgument("entryId") { type = NavType.IntType } )
        ) { backStackEntry ->
            EntryScreen(
                viewModel = viewModel,
                entryId = backStackEntry.arguments?.getInt("entryId") ?: 0,
                onFabChange = onFabChange,
                navController = navController
            )
            onValueChange(Strings.ScreenTitles.EntryScreen)
        }
    }
}