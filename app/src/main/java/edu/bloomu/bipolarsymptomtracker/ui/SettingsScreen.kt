package edu.bloomu.bipolarsymptomtracker.ui

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.ui.components.SaveFab
import edu.bloomu.bipolarsymptomtracker.ui.theme.AppText

@Composable
fun SettingsScreen(
    viewModel: EntryViewModel,
    onFabChange: (fab: @Composable () -> Unit) -> Unit
) {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }
    val editor = sharedPreferences.edit()

    val usersName = sharedPreferences.getString(AppText.SharedPrefKeys.UserName, "")?: ""
    val cycleLength = sharedPreferences.getInt(AppText.SharedPrefKeys.CycleLength, 0)?: 0

    var clearEntries = false

    val save = {
        editor.putBoolean(AppText.SharedPrefKeys.SetupCompleted, true)
        editor.putString(AppText.SharedPrefKeys.UserName, usersName)
        editor.putInt(AppText.SharedPrefKeys.CycleLength, cycleLength)
        editor.apply()

        if (clearEntries) viewModel.clearEntries()
    }

    LaunchedEffect(Unit) {
        onFabChange {
            SaveFab(
                onClick = save
            )
        }
    }

    Column {
        Row {
            // app name
        }
        Row {

        }
        Row {
            // change name, adjust cycle length
            TextField(
                value = usersName,
                onValueChange = {
                    editor.putString(AppText.SharedPrefKeys.UserName, usersName)
                    editor.apply()
                }
            )

        }
        Row {
            // clear entries
        }
    }
}