package edu.bloomu.bipolarsymptomtracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.R
import edu.bloomu.bipolarsymptomtracker.model.Mood
import edu.bloomu.bipolarsymptomtracker.model.Symptoms
import edu.bloomu.bipolarsymptomtracker.ui.components.CancelFab
import edu.bloomu.bipolarsymptomtracker.ui.components.EditFab
import edu.bloomu.bipolarsymptomtracker.ui.components.MoodDial
import edu.bloomu.bipolarsymptomtracker.ui.components.RowDivider
import edu.bloomu.bipolarsymptomtracker.ui.components.SaveFab
import edu.bloomu.bipolarsymptomtracker.ui.components.SymptomList
import edu.bloomu.bipolarsymptomtracker.ui.theme.BipolarSymptomTrackerTheme

class EntryScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BipolarSymptomTrackerTheme {
                //MainContainer()
            }
        }
    }
}

// SECTIONS OF THIS SCREEN:

// 1. Top - contains screen title, date of entry, edit button
// 2. Mood entry
// 3. Symptom list
// 4. Med check
// 5. Drug check
// 6. Save

@Composable
fun MainContainer2() {
    var editMode by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { editMode = !editMode }) {
                if (editMode) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Save"
                    )
                }
                else {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit"
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End, // Positions FAB to the bottom-right
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(paddingValues) // Apply padding from the scaffold
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
    )
}