package edu.bloomu.bipolarsymptomtracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.nav.NavigationItem
import edu.bloomu.bipolarsymptomtracker.ui.components.EntryCard
import edu.bloomu.bipolarsymptomtracker.ui.components.NewEntryFab
import edu.bloomu.bipolarsymptomtracker.ui.theme.Painters
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.Units

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
        Column(
            modifier = Modifier
                .padding(
                    vertical = 24.dp,
                    horizontal = 36.dp
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = Painters.Filled.ErrorFace,
                    contentDescription = Strings.Screens.Entries.Error.iconAlt,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .size(Units.Icons.ExxxtraLarge)
                        .padding(Units.Padding.Icon)
                )
                Text(
                    text = Strings.Screens.Entries.Error.heading,
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp
                        )
                )
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        horizontal = Units.Padding.Icon
                    )
            ) {
                Text(
                    text = Strings.Screens.Entries.Error.subheading,
                    style = MaterialTheme.typography.displaySmall
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        vertical = Units.Scaffold.Fab.Size + Units.Scaffold.Fab.Padding,
                        horizontal = Units.Scaffold.Fab.Size
                    )
                    .fillMaxWidth()
            ) {

                Text(
                    text = Strings.Screens.Entries.Error.hint,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .padding(
                            end = 12.dp,
                            bottom = 14.dp,
                        )
                )
                Icon(
                    painter = Painters.Filled.CasualArrow,
                    contentDescription = "Right arrow",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .rotate(120f)
                        .size(Units.Icons.Standard)
                )
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
                            route = NavigationItem.EditEntry.route + "/" + entry.id
                        )
                    },
                    onDelete = { viewModel.deleteEntry(entry) },
                    modifier = Modifier
                )
            }
        }
    }
}