package edu.bloomu.bipolarsymptomtracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.db.Entry
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.model.Mood
import edu.bloomu.bipolarsymptomtracker.model.Symptoms
import edu.bloomu.bipolarsymptomtracker.ui.components.MoodDial
import edu.bloomu.bipolarsymptomtracker.ui.components.RowDivider
import edu.bloomu.bipolarsymptomtracker.ui.components.SymptomList

@Composable
fun EntryScreen(
    viewModel: EntryViewModel,
    entry: Entry
) {
    //var editMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            //.padding(paddingValues) // Apply padding from the scaffold
            .padding(8.dp) // Additional padding for the content
    ) {
        Row { // Header
            RowDivider()
        }
        Row { // Mood
            MoodDial(Mood())
            RowDivider()
        }
        Row { // Symptoms
            val context = LocalContext.current
            SymptomList(
                symptoms = Symptoms(context),
                modifier = Modifier
            )
            RowDivider()
        }
        Row { // Med Check
            RowDivider()
        }
        Row { // Drug Check
            RowDivider()
        }

    }
}