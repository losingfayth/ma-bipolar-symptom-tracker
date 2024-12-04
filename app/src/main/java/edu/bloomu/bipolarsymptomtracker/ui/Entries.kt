package edu.bloomu.bipolarsymptomtracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bloomu.bipolarsymptomtracker.db.Entry
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.model.Mood
import edu.bloomu.bipolarsymptomtracker.model.Symptoms
import edu.bloomu.bipolarsymptomtracker.nav.NavigationItem
import edu.bloomu.bipolarsymptomtracker.ui.components.EntryCard
import edu.bloomu.bipolarsymptomtracker.ui.components.NewEntryFab
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Composable
fun Entries(
    viewModel: EntryViewModel,
    navController: NavController,
    onFabChange: (fab: @Composable () -> Unit) -> Unit
) {
    LaunchedEffect(Unit) {
        onFabChange {
            NewEntryFab(
                viewModel = viewModel,
                navController = navController
            )
        }
    }

    val entries by viewModel.entries.collectAsState()

    if (entries.isEmpty()) {
        Column {
            Row {
                //TODO("actual 'no entries' display")
                Text(
                    text = "HEY, FUCKO. ADD AN ENTRY",
                    style = MaterialTheme.typography.displayLarge
                )
            }
            Row {
                val context = LocalContext.current
                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            viewModel.insertEntry(
                                Entry(
                                    date = LocalDateTime.now().toString(),
                                    symptoms = Symptoms(context),
                                    mood = Mood()
                                )
                            )

                            viewModel.entries.collectLatest { entries ->
                                if (entries.isNotEmpty())
                                navController.navigate(
                                    route = NavigationItem.EntryScreen.route + "/" + viewModel.getFirstEntry().id
                                )
                            }
                        }
                    }
                ) {
                    Text(
                        text = "NEW ENTRY",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
    else {
        LazyColumn(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                )
        ) {
            items(entries) { entry ->
                EntryCard(
                    entry = entry,
                    onClick = {
                        navController.navigate(
                            route = NavigationItem.EntryScreen.route + "/" + entry.id
                        )
                    },
                    modifier = Modifier
                )
            }
        }
    }
}