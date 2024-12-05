package edu.bloomu.bipolarsymptomtracker.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import edu.bloomu.bipolarsymptomtracker.R
import edu.bloomu.bipolarsymptomtracker.db.AppDatabase
import edu.bloomu.bipolarsymptomtracker.db.DatabaseInstance
import edu.bloomu.bipolarsymptomtracker.db.EntryRepository
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.nav.AppNavHost
import edu.bloomu.bipolarsymptomtracker.nav.NavigationItem
import edu.bloomu.bipolarsymptomtracker.ui.components.NavButton
import edu.bloomu.bipolarsymptomtracker.ui.theme.BipolarSymptomTrackerTheme
import edu.bloomu.bipolarsymptomtracker.ui.theme.Painters
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.Units
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_icon_button_dark
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_icon_button_light

/**
 * biPerfect - A Bipolar Disorder symptom tracker and analyzer
 *
 * This app allows users to log their Bipolar Symptoms (from a set list of common symptoms)
 * and offers some rudimentary analysis based on vibes (my vibes, a.k.a. algorithm)
 *
 * @author fayth quinn
 */
class MainActivity : ComponentActivity() {
    private lateinit var database: AppDatabase
    private lateinit var repository: EntryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = DatabaseInstance.getDatabase(this)
        repository = EntryRepository(database.entryDao())
        enableEdgeToEdge()
        setContent {
            BipolarSymptomTrackerTheme {
                MainContainer(repository)
            }
        }
    }
}

/**
 * Main Container
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer(
    repository: EntryRepository
) {
    // Handles navigation between the screens
    val navController = rememberNavController()

    // Top app bar dynamic screen title
    var title by remember{ mutableStateOf("") }

    // This handles the databasing
    val viewModel = EntryViewModel(repository)

    // The floating action button (fab) can be updated by the child composables
    var fab by remember { mutableStateOf<@Composable (() -> Unit)>({}) }

    // These functions allow child composables to "click" the navigation buttons
    // and change their appearance
    var analysisNavSelect: (() -> Unit)? = null
    var analysisNavUnselect: (() -> Unit)? = null
    var entriesNavSelect: (() -> Unit)? = null
    var entriesNavUnselect: (() -> Unit)? = null

    // Allows the settings button in the top app bar to dynamically update its color
    // depending on whether or not the user is in the settings screen
    var inSettings by remember { mutableStateOf(false) }

    // User's name, cycle length are stored in shared preferences
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences(Strings.SharedPrefKeys.SharedPreferences, Context.MODE_PRIVATE)
    }

    // ...as well as whether the user has already set up the app
    var setupComplete by remember{
        mutableStateOf(
            sharedPreferences.getBoolean(Strings.SharedPrefKeys.SetupCompleted, false)
        )
    }

    // Scaffold provides a container for the app content as well as implementing
    // a top app bar (for title display), a bottom app bar (for navigation) and a floating action button
    Scaffold(
        bottomBar = {
            if (setupComplete) {
                BottomAppBar(
                    actions = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            // Main screen 1: Analysis
                            NavButton(
                                onClick = {
                                    navController.navigate(NavigationItem.Analysis.route)
                                },
                                icon = Painters.Outlined.Analytics,
                                text = Strings.NavigationBar.Analysis,
                                onSelect = { method ->
                                    analysisNavSelect = method // Click the button, update colors
                                },
                                onUnselect = { method ->
                                    analysisNavUnselect = method // Click the button, update colors
                                },
                                initiallySelected = true
                            )
                            // Main screen 2: Entries
                            //  Highlighted when user is in Entries or SingleEntry
                            NavButton(
                                onClick = {
                                    navController.navigate(NavigationItem.Entries.route)
                                },
                                icon = Painters.Outlined.List,
                                text = Strings.NavigationBar.Entries,
                                onSelect = { method ->
                                    entriesNavSelect = method // Click the button, update colors
                                },
                                onUnselect = { method ->
                                    entriesNavUnselect = method // Click the button, update colors
                                }
                            )
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        },
        topBar = {
            // The top app bar is not necessary on the Welcome screen (before the user has
            // initialized the app)
            if (setupComplete) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                            ,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            // Title bar: App name
                            Text(
                                text = stringResource(R.string.app_name) + "  • ",
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier
                                    .padding(
                                        start = 12.dp,
                                        end = 0.dp,
                                        top = 24.dp,
                                        bottom = 24.dp
                                    )
                            )
                            // Title bar: Screen title
                            Text(
                                text = title,
                                style = MaterialTheme.typography.headlineLarge,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier
                                    .padding(
                                        start = 6.dp,
                                        end = 0.dp,
                                        top = 24.dp,
                                        bottom = 24.dp
                                    )
                            )
                        }
                    },
                    actions = {
                        // Settings button
                        IconButton(
                            onClick = {
                                navController.navigate(NavigationItem.Settings.route)
                            },
                            colors =
                                if (inSettings) md_theme_light_icon_button_dark
                                else md_theme_light_icon_button_light,
                            modifier = Modifier
                                .padding(
                                    vertical = 8.dp,
                                    horizontal = 12.dp
                                )
                                .size(Units.Icons.Large)
                        ) {
                            Icon(
                                Painters.Outlined.Settings,
                                contentDescription =
                                    Strings.Scaffold.TopAppBar.Buttons.Settings.IconDesc
                            )
                        }
                    },
                    // App bar should stay in play when user is scrolling
                    scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier
                        .wrapContentHeight()
                )
            }
        },
        floatingActionButton = { fab() },   // Fab updated dynamically by children composables
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        // Custom navigation host handles switching screens
        AppNavHost(
            modifier = Modifier
                .padding(paddingValues), // Padding so scaffold doesn't obscure content
            navController = navController, // Initialized controller
            viewModel = viewModel, // Database access
            onValueChange = { // Dynamic title display and navigation button clicking
                value -> title = value  // Child process updates the title value

                // New value is used to click buttons to indicate to user what screen they're in
                if (value == Strings.ScreenTitles.Analysis) analysisNavSelect?.invoke()
                else analysisNavUnselect?.invoke()

                if (value == Strings.ScreenTitles.EntryScreen
                    || value == Strings.ScreenTitles.Entries)
                    entriesNavSelect?.invoke()
                else entriesNavUnselect?.invoke()

                if (value == Strings.ScreenTitles.Settings) inSettings = true
                else inSettings = false
            },
            // Child process provides a fab when it is launched
            onFabChange = { newFab -> fab = newFab },
            onClick = { setupComplete = true }, // Function for welcome screen to close itself
            startDestination =
                if (setupComplete) NavigationItem.Analysis.route // Analysis screen as home screen
                else NavigationItem.Welcome.route   // Show the welcome screen if app not setup
        )
    }
}