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
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_button_error
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_button_secondary

@Composable
fun SuccessDialog(
    confirmText: String,
    bodyText: String,
    isVisible: Boolean, // State to control dialog visibility
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
                    text = confirmText,
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
                        text = bodyText,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = modifier
                    )
                }
            },
            confirmButton = {},
            dismissButton = {
                Button(
                    onClick = { onConfirm() },
                    colors = md_theme_light_button_secondary
                ) {
                    Text(
                        text = Strings.Settings.SuccessDialog.SaveSettings.Button,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            },
            modifier = Modifier
                .size(
                    width = 600.dp,
                    height = 360.dp
                )
        )
    }
}

@Composable
fun ConfirmDialog(
    confirmText: String,
    cancelText: String,
    isVisible: Boolean, // State to control dialog visibility
    onConfirm: () -> Unit, // Action to perform on "Confirm"
    onCancel: () -> Unit // Default: dismiss dialog
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
                    text = confirmText,
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
                        text = Strings.Settings.ConfirmClearDialog.Message,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.error,
                        modifier = modifier
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { onConfirm() },
                    colors = md_theme_light_button_error
                ) {
                    Text(
                        text = Strings.Settings.ConfirmClearDialog.ConfirmText,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { onCancel() },
                    colors = md_theme_light_button_secondary
                ) {
                    Text(
                        text = cancelText,
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