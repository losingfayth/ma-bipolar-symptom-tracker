package edu.bloomu.bipolarsymptomtracker.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.R
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.ui.components.ConfirmDialog
import edu.bloomu.bipolarsymptomtracker.ui.components.CycleSlider
import edu.bloomu.bipolarsymptomtracker.ui.components.SaveFab
import edu.bloomu.bipolarsymptomtracker.ui.components.SuccessDialog
import edu.bloomu.bipolarsymptomtracker.ui.components.UsernameField
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.Units
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_button_error

@Composable
fun SettingsScreen(
    onFabChange: (fab: @Composable () -> Unit) -> Unit,
    onClick: () -> Unit,
    viewModel: EntryViewModel
) {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences(Strings.SharedPrefKeys.sharedPrefs, Context.MODE_PRIVATE)
    }
    val editor = sharedPreferences.edit()

    var usersName by remember {
        mutableStateOf(sharedPreferences.getString(Strings.SharedPrefKeys.userName, ""))
    }
    var cycleLength by remember {
        mutableIntStateOf(sharedPreferences.getInt(Strings.SharedPrefKeys.cycleLength, 0))
    }

    val entries by viewModel.entries.collectAsState()

    var showConfirmDialog by remember { mutableStateOf(false) }
    var showClearSuccessDialog by remember { mutableStateOf(false) }
    var showSaveSuccessDialog by remember { mutableStateOf(false) }
    var entriesIsNotEmpty by remember { mutableStateOf(entries.isNotEmpty()) }

    LaunchedEffect(Unit) {
        onFabChange {
            SaveFab(
                onClick = {
                    editor.putString(Strings.SharedPrefKeys.userName, usersName)
                    editor.putInt(Strings.SharedPrefKeys.cycleLength, cycleLength)
                    editor.apply()
                    showSaveSuccessDialog = true
                }
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Units.Row.Padding.ExtraHorizontal,
                vertical = Units.Row.Padding.Vertical
            )

        Row(
            modifier = modifier
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.displayLarge.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                    ) { append(stringResource(id = R.string.app_name))}
                }
            )
        }
        Row(
            modifier = modifier
        ) {
            Text(
                text = Strings.Screens.Settings.name,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            usersName?.let {
                UsernameField(
                    currName = usersName!!,
                    onValueChange = { newValue ->
                        usersName = newValue
                    }
                )
            }
        }
        Row(
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = Strings.Screens.Settings.cycleLength,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(
                            vertical = Units.Row.Padding.Vertical
                        )
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 36.dp
                        )
                ) {
                    Text(
                        text = cycleLength.toString() + " days",
                        style = MaterialTheme.typography.labelLarge
                    )
                    CycleSlider(
                        value = cycleLength,
                        onValueChange = { newValue -> cycleLength = newValue }
                    )

                }
            }
        }
        if (entriesIsNotEmpty) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .padding(
                        vertical = 64.dp
                    )
            ) {
                Button(
                    onClick = {
                        showConfirmDialog = true
                        onClick()
                    },
                    colors = md_theme_button_error,
                    shape = RoundedCornerShape(
                        size = 8.dp
                    ),
                    modifier = Modifier
                        .size(
                            width = 300.dp,
                            height = 88.dp
                        )
                ) {
                    Text(
                        text = Strings.Screens.Settings.clearEntries,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }

        ConfirmDialog (
            isVisible = showConfirmDialog,
            onConfirm = {
                viewModel.clearEntries()
                showClearSuccessDialog = true
                entriesIsNotEmpty = false
                showConfirmDialog = false
            },
            onCancel = { showConfirmDialog = false },
            confirmButton = Strings.Screens.Settings.ConfirmDialog.confirmButton,
            cancelButton = Strings.Screens.Settings.ConfirmDialog.cancelButton
        )

        SuccessDialog(
            isVisible = showClearSuccessDialog,
            onConfirm = { showClearSuccessDialog = false },
            title = Strings.Screens.Settings.SuccessDialog.ClearEntries.title,
            message = Strings.Screens.Settings.SuccessDialog.ClearEntries.body,
        )

        SuccessDialog(
            isVisible = showSaveSuccessDialog,
            onConfirm = { showSaveSuccessDialog = false },
            title = Strings.Screens.Settings.SuccessDialog.SaveSettings.title,
            message = Strings.Screens.Settings.SuccessDialog.SaveSettings.body
        )
    }
}