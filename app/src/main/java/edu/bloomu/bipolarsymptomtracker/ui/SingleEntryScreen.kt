package edu.bloomu.bipolarsymptomtracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.model.formatDate
import edu.bloomu.bipolarsymptomtracker.model.formatTime
import edu.bloomu.bipolarsymptomtracker.nav.NavigationItem
import edu.bloomu.bipolarsymptomtracker.ui.components.MoodDial
import edu.bloomu.bipolarsymptomtracker.ui.components.SaveFab
import edu.bloomu.bipolarsymptomtracker.ui.components.StateAnalysisIcon
import edu.bloomu.bipolarsymptomtracker.ui.components.SymptomList
import edu.bloomu.bipolarsymptomtracker.ui.components.TextSwitchButton
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings

@Composable
fun EntryScreen(
    viewModel: EntryViewModel,
    navController: NavController,
    entryId: Int,
    onFabChange: (fab: @Composable () -> Unit) -> Unit
) {
    val entry by remember { mutableStateOf(viewModel.getEntryById(entryId)) }

    val save = {
        if (entry != null) viewModel.insertEntry(entry!!)
        navController.navigate(NavigationItem.Entries.route)
    }

    LaunchedEffect(Unit) {
        onFabChange {
            SaveFab(
                onClick = save
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if (entry != null) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    )
            ) {
                val date = formatDate(entry!!.date)
                val time = formatTime(entry!!.date)

                StateAnalysisIcon(
                    entry!!.analysis,
                    color = false,
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp
                        )
                )
                if (date != null) {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                if (time != null) {
                    Text(
                        text = time,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .padding(
                                horizontal = 18.dp
                            )
                    )
                }
            }
            Row { // Mood
                MoodDial(
                    mood = entry!!.mood
                )
            }
            Row { // Symptoms
                SymptomList(
                    symptoms = entry!!.symptoms,
                    modifier = Modifier
                )
            }
            val configuration = LocalConfiguration.current
            val buttonWidth = (configuration.screenWidthDp / 24) * 13
            val switchButtonModifier = Modifier
                .padding(
                    horizontal = 16.dp
                )
                .size(
                    width = buttonWidth.dp,
                    height = buttonWidth.dp / 2
                )
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    )
            ) {
                TextSwitchButton(
                    text = Strings.SingleEntryScreen.MedsSwitch,
                    initiallySelected = entry!!.meds,
                    onClick = { entry!!.meds = !entry!!.meds },
                    modifier = switchButtonModifier
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    )
            ) {
                TextSwitchButton(
                    text = Strings.SingleEntryScreen.DrugSwitch,
                    initiallySelected = entry!!.drugs,
                    onClick = { entry!!.drugs = !entry!!.drugs },
                    modifier = switchButtonModifier
                )
            }
        }
    }
}