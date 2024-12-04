package edu.bloomu.bipolarsymptomtracker.ui

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import edu.bloomu.bipolarsymptomtracker.ui.components.NumberPicker
import edu.bloomu.bipolarsymptomtracker.ui.theme.AppText

@Composable
fun InitialSetup() {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }
    val editor = sharedPreferences.edit()

    var usersName by remember { mutableStateOf(AppText.WelcomeText.NameEntry) }
    var cycleLength by remember { mutableIntStateOf(15) }

    Column {
        Row {
            // hello, you
        }
        Row {
            // about the app, etc
        }
        Row {
            TextField(
                value = usersName,
                onValueChange = { newValue ->
                    usersName = newValue
                }
            )
            NumberPicker(
                max = 90,
                min = 1,
                start = cycleLength,
                onValueChange = { newValue ->
                    cycleLength = newValue
                },
                content = {
                    Text(
                        text = AppText.WelcomeText.SelectCycleLength
                    )
                }
            )
        }
        Row {
            // Disclaimer
        }
        Row {
            // let's get started
            Button(
                onClick = {
                    editor.putInt(AppText.SharedPrefKeys.CycleLength, cycleLength)
                    editor.putString(AppText.SharedPrefKeys.UserName,
                        if (usersName == AppText.WelcomeText.NameEntry) "User" else usersName)
                    editor.putBoolean(AppText.SharedPrefKeys.SetupCompleted, true)
                    editor.apply()
                }
            ) {
                Text(
                    text = "I'm in your walls"
                )
            }
        }
    }
}