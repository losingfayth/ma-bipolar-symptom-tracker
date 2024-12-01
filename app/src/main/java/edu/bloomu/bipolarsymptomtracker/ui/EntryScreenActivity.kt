package edu.bloomu.bipolarsymptomtracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.bloomu.bipolarsymptomtracker.ui.theme.BipolarSymptomTrackerTheme

class EntryScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BipolarSymptomTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    BipolarSymptomTrackerTheme {
        Greeting2("Android")
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
fun MainContainer() {
    Column {
        Row { // header

        }
        Row { // mood

        }
        Row { // symptoms

        }
        Row { // med check

        }
        Row { // drug check

        }
        Row { // save button

        }
    }
}