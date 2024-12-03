package edu.bloomu.bipolarsymptomtracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import edu.bloomu.bipolarsymptomtracker.ui.components.NavButton
import edu.bloomu.bipolarsymptomtracker.ui.theme.BipolarSymptomTrackerTheme
import edu.bloomu.bipolarsymptomtracker.ui.theme.Icons

class MainActivity : ComponentActivity() {
    lateinit var database: AppDatabase
    lateinit var repository: EntryRepository

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
    var title by remember{ mutableStateOf("Your analysis") }

    var viewModel = EntryViewModel(repository)

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
                                title = "Your analysis"
                                      },
                            icon = Icons.Outlined.Analytics,
                            text = "Analysis",
                            initiallySelected = true
                        )
                        NavButton(
                            onClick = {
                                navController.navigate(NavigationItem.Entries.route);
                                title = "View entries"
                                      },
                            icon = Icons.Outlined.List,
                            text = "Entries"
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
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f)) // Spacer on the left
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .padding(8.dp, 16.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f)) // Spacer on the right
                    }
                },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(NavigationItem.Settings.route) },
                        modifier = Modifier
                            .padding(8.dp, 16.dp)
                    ) {
                        Icon(Icons.Outlined.Settings, contentDescription = "Localized description")
                    }
                },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        AppNavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            viewModel = viewModel
        )
    }
}