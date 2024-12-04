package edu.bloomu.bipolarsymptomtracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.model.FormatDate
import edu.bloomu.bipolarsymptomtracker.model.FormatTime
import edu.bloomu.bipolarsymptomtracker.model.State
import edu.bloomu.bipolarsymptomtracker.ui.components.MoodDial
import edu.bloomu.bipolarsymptomtracker.ui.components.RowDivider
import edu.bloomu.bipolarsymptomtracker.ui.components.SwitchButton
import edu.bloomu.bipolarsymptomtracker.ui.components.SymptomList
import edu.bloomu.bipolarsymptomtracker.ui.theme.Icons

@Composable
fun EntryScreen(
    viewModel: EntryViewModel,
    entryId: Int
) {
    //var editMode by remember { mutableStateOf(false) }

    val entry = viewModel.getEntryById(entryId)

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(8.dp) // Additional padding for the content
            .verticalScroll(rememberScrollState())
    ) {
        if (entry != null) {
            Row {

                val date = FormatDate(entry.date)
                val time = FormatTime(entry.date)

                Icon(
                    painter = when(entry.analysis) {
                        State.MANIC -> Icons.Outlined.AnalysisManic
                        State.HYPO_MANIC -> Icons.Outlined.AnalysisHypoManic
                        State.DEPRESSIVE -> Icons.Outlined.AnalysisDepressive
                        State.HYPO_DEPRESSIVE -> Icons.Outlined.AnalysisHypoDepressive
                        State.UNSTABLE -> Icons.Outlined.AnalysisUnstable
                        else -> Icons.Outlined.AnalysisNone
                    },
                    contentDescription = ""
                )
                if (date != null) {
                    Text(date)
                }
                if (time != null) {
                    Text(time)
                }
            }
            Row { // Mood
                MoodDial(
                    mood = entry.mood
                )
            }
            Row { // Symptoms
                SymptomList(
                    symptoms = entry.symptoms,
                    modifier = Modifier
                )
            }
            Row { // Med Check
                SwitchButton(
                    text = "",
                    initiallySelected = entry.meds,
                    onClick = { entry.meds = !entry.meds }
                )
                RowDivider()
            }
            Row { // Drug Check
                SwitchButton(
                    text = "",
                    initiallySelected = entry.drugs,
                    onClick = { entry.drugs = !entry.drugs }
                )
                RowDivider()
            }
            Row {
                Button(onClick = {
                    viewModel.insertEntry(entry)
                }) {
                    Text("Save Entry")
                }
            }
        }
    }
}