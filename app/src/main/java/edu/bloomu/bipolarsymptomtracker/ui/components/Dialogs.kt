package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.ui.theme.Painters
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.Units
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_button_error
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_button_secondary

/**
 * Allows a screen to indicate to a user that an action has performed successfully
 *
 * @param title Dialog title
 * @param message Message to display to user
 * @param isVisible Whether to show the dialog
 * @param onConfirm Closes the dialog
 */
@Composable
fun SuccessDialog(
    title: String,
    message: String,
    isVisible: Boolean,
    onConfirm: () -> Unit
) {
    if (isVisible) {
        val modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )

        AlertDialog(
            onDismissRequest = { onConfirm() }, // Dismiss when clicked outside
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = modifier
                )
            },
            text = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = message,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = modifier
                    )
                }
            },
            confirmButton = {},
            dismissButton = {
                Button(
                    onClick = { onConfirm() },
                    colors = md_theme_button_secondary
                ) {
                    Text(
                        text = Strings.Components.Dialogs.Success.dismiss,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        )
    }
}

/**
 * Allows a screen to prompt for user confirmation after an action is taken
 *
 * @param confirmButton The text for the confirm button
 * @param cancelButton The text for the cancel button
 * @param title The title for the dialog
 * @param message The message to display to the user
 * @param isVisible Whether to show the dialog
 * @param onConfirm Allows parent to take action and closes dialog
 * @param onCancel Closes dialog
 */
@Composable
fun ConfirmDialog(
    confirmButton: String = Strings.Components.Dialogs.Confirm.confirm,
    cancelButton: String = Strings.Components.Dialogs.Confirm.cancel,
    title: String = Strings.Components.Dialogs.Confirm.title,
    message: String = Strings.Components.Dialogs.Confirm.message,
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    if (isVisible) {
        val modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )

        AlertDialog(
            onDismissRequest = { onCancel() }, // Dismiss when clicked outside
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = modifier
                )
            },
            text = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = Painters.Filled.Alert,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .size(
                                size = Units.Icons.Large
                            )
                    )
                    Text(
                        text = message,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.error,
                        modifier = modifier
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { onConfirm() },
                    colors = md_theme_button_error
                ) {
                    Text(
                        text = confirmButton,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { onCancel() },
                    colors = md_theme_button_secondary
                ) {
                    Text(
                        text = cancelButton,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            },
            modifier = Modifier
                .size(
                    width = 600.dp,
                    height = 400.dp
                )
        )
    }
}