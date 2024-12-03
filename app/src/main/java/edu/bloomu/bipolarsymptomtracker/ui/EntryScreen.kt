package edu.bloomu.bipolarsymptomtracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.ui.components.MoodDial
import edu.bloomu.bipolarsymptomtracker.ui.components.RowDivider
import edu.bloomu.bipolarsymptomtracker.ui.components.SymptomList

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
    ) {
        Row { // Mood
            if (entry != null) {
                MoodDial(
                    mood = entry.mood
                )
            }
        }
        Row { // Symptoms
            if (entry != null) {
                SymptomList(
                    symptoms = entry.symptoms,
                    modifier = Modifier
                )
            }
        }
        Row { // Med Check
            RowDivider()
        }
        Row { // Drug Check
            RowDivider()
        }
        Row {
            Button(onClick = {
                if (entry != null) {
                    viewModel.insertEntry(entry)
                }
            }) {
                Text("Save Entry")
            }
        }
    }
}