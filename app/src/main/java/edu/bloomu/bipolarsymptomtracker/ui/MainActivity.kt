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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer(
    repository: EntryRepository
) {
    val navController = rememberNavController()
    var title by remember{ mutableStateOf("") }

    val viewModel = EntryViewModel(repository)

    var fab by remember { mutableStateOf<@Composable (() -> Unit)>({}) }

    var analysisNavSelect: (() -> Unit)? = null
    var analysisNavUnselect: (() -> Unit)? = null
    var entriesNavSelect: (() -> Unit)? = null
    var entriesNavUnselect: (() -> Unit)? = null
    var inSettings by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences(Strings.SharedPrefKeys.SharedPreferences, Context.MODE_PRIVATE)
    }

    var setupComplete by remember{
        mutableStateOf(
            sharedPreferences.getBoolean(Strings.SharedPrefKeys.SetupCompleted, false)
        )
    }

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
                            NavButton(
                                onClick = {
                                    navController.navigate(NavigationItem.Analysis.route)
                                },
                                icon = Painters.Outlined.Analytics,
                                text = Strings.NavigationBar.Analysis,
                                onSelect = { method ->
                                    analysisNavSelect = method
                                },
                                onUnselect = { method ->
                                    analysisNavUnselect = method
                                },
                                initiallySelected = true
                            )
                            NavButton(
                                onClick = {
                                    navController.navigate(NavigationItem.Entries.route)
                                },
                                icon = Painters.Outlined.List,
                                text = Strings.NavigationBar.Entries,
                                onSelect = { method ->
                                    entriesNavSelect = method
                                },
                                onUnselect = { method ->
                                    entriesNavUnselect = method
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
                        IconButton(
                            onClick = {
                                navController.navigate(NavigationItem.Settings.route)
                            },
                            colors = if (inSettings) md_theme_light_icon_button_dark else md_theme_light_icon_button_light,
                            modifier = Modifier
                                .padding(
                                    vertical = 8.dp,
                                    horizontal = 12.dp
                                )
                                .size(Units.Icons.Large)
                        ) {
                            Icon(
                                Painters.Outlined.Settings,
                                contentDescription = Strings.Scaffold.TopAppBar.Buttons.Settings.IconDesc
                            )
                        }
                    },
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
        floatingActionButton = {
            fab()
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        AppNavHost(
            modifier = Modifier
                .padding(paddingValues),
            innerPadding = paddingValues,
            navController = navController,
            viewModel = viewModel,
            onValueChange = {
                value -> title = value
                if (value == Strings.ScreenTitles.Analysis) analysisNavSelect?.invoke()
                else analysisNavUnselect?.invoke()

                if (value == Strings.ScreenTitles.EntryScreen || value == Strings.ScreenTitles.Entries) entriesNavSelect?.invoke()
                else entriesNavUnselect?.invoke()

                if (value == Strings.ScreenTitles.Settings) inSettings = true
                else inSettings = false
            },
            onFabChange = { newFab ->
                fab = newFab
            },
            onClick = {
                setupComplete = true
            },
            startDestination = if (setupComplete) NavigationItem.Analysis.route else NavigationItem.Welcome.route
        )
    }
}