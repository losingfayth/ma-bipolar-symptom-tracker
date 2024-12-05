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
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import edu.bloomu.bipolarsymptomtracker.ui.components.SaveFab
import edu.bloomu.bipolarsymptomtracker.ui.components.SuccessDialog
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.Units
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_button_error

@Composable
fun SettingsScreen(
    onFabChange: (fab: @Composable () -> Unit) -> Unit,
    onClick: () -> Unit,
    viewModel: EntryViewModel
) {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences(Strings.SharedPrefKeys.SharedPreferences, Context.MODE_PRIVATE)
    }
    val editor = sharedPreferences.edit()

    var usersName by remember {
        mutableStateOf(sharedPreferences.getString(Strings.SharedPrefKeys.UserName, ""))
    }
    var cycleLength by remember {
        mutableIntStateOf(sharedPreferences.getInt(Strings.SharedPrefKeys.CycleLength, 0))
    }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showClearSuccessDialog by remember { mutableStateOf(false) }
    var showSaveSuccessDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        onFabChange {
            SaveFab(
                onClick = {
                    editor.putString(Strings.SharedPrefKeys.UserName, usersName)
                    editor.putInt(Strings.SharedPrefKeys.CycleLength, cycleLength)
                    editor.apply()
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
                text = Strings.Settings.Name,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            usersName?.let {
                TextField(
                    value = it,
                    onValueChange = { newValue ->
                        usersName = newValue
                    },
                    textStyle = MaterialTheme.typography.titleMedium,
                    placeholder = {
                        Text(
                            text = Strings.WelcomeText.NameEntry,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
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
                    text = Strings.Settings.CycleLength,
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
                    Slider(
                        value = cycleLength.toFloat(),
                        onValueChange = { cycleLength = it.toInt() },
                        steps = 98,
                        valueRange = 1f..62f,
                        modifier = Modifier
                            .padding(
                                horizontal = 8.dp
                            )
                            .fillMaxWidth()
                    )

                }
            }
        }
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
                colors = md_theme_light_button_error,
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
                    text = Strings.Settings.ClearEntries,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        ConfirmDialog (
            isVisible = showConfirmDialog,
            onConfirm = {
                viewModel.clearEntries()
                showConfirmDialog = false
            },
            onCancel = {
                showConfirmDialog = false
            },
            confirmText = Strings.Settings.ConfirmClearDialog.ConfirmText,
            cancelText = Strings.Settings.ConfirmClearDialog.CancelText
        )

        SuccessDialog(
            isVisible = showClearSuccessDialog,
            onConfirm = { showClearSuccessDialog = false },
            confirmText = Strings.Settings.SuccessDialog.ClearEntries.Title,
            bodyText = Strings.Settings.SuccessDialog.ClearEntries.Body
        )

        SuccessDialog(
            isVisible = showClearSuccessDialog,
            onConfirm = { showClearSuccessDialog = false },
            confirmText = Strings.Settings.SuccessDialog.SaveSettings.Title,
            bodyText = Strings.Settings.SuccessDialog.SaveSettings.Body
        )
    }
}