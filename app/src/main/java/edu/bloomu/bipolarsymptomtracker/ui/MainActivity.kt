package edu.bloomu.bipolarsymptomtracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import edu.bloomu.bipolarsymptomtracker.db.AppDatabase
import edu.bloomu.bipolarsymptomtracker.db.DatabaseInstance
import edu.bloomu.bipolarsymptomtracker.db.EntryRepository
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.nav.AppNavHost
import edu.bloomu.bipolarsymptomtracker.nav.NavigationItem
import edu.bloomu.bipolarsymptomtracker.ui.components.LargeFabDefault
import edu.bloomu.bipolarsymptomtracker.ui.components.NavButton
import edu.bloomu.bipolarsymptomtracker.ui.theme.AppText
import edu.bloomu.bipolarsymptomtracker.ui.theme.BipolarSymptomTrackerTheme
import edu.bloomu.bipolarsymptomtracker.ui.theme.Icons

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

    var fab by remember { mutableStateOf<@Composable (() -> Unit)>({ LargeFabDefault(
        onClick = {},
        icon = Icons.Outlined.Exit,
        desc = ""
    ) }) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        NavButton(
                            onClick = {
                                navController.navigate(NavigationItem.Analysis.route);
                                      },
                            icon = Icons.Outlined.Analytics,
                            text = AppText.NavigationBar.Analysis,
                            selected = title == AppText.ScreenTitles.Analysis
                        )
                        NavButton(
                            onClick = {
                                navController.navigate(NavigationItem.Entries.route);
                                      },
                            icon = Icons.Outlined.List,
                            text = AppText.NavigationBar.Entries,
                            selected = title == AppText.ScreenTitles.Entries || title == AppText.ScreenTitles.EntryScreen
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        },
        topBar = {
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
                            text = title,
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier
                                .padding(
                                    vertical = 24.dp,
                                    horizontal = 16.dp
                                )
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(NavigationItem.Settings.route) },
                        modifier = Modifier

                    ) {
                        Icon(Icons.Outlined.Settings, contentDescription = "Localized description")
                    }
                },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier
                    .wrapContentHeight()
            )
        },
        floatingActionButton = {
            fab()
        }
    ) { paddingValues ->
        AppNavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            viewModel = viewModel,
            onValueChange = { value -> title = value },
            onFabChange = { newFab ->
                fab = newFab
            }
        )
    }
}