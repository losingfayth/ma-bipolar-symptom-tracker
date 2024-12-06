package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.db.Entry
import edu.bloomu.bipolarsymptomtracker.model.Symptom
import edu.bloomu.bipolarsymptomtracker.model.formatDate
import edu.bloomu.bipolarsymptomtracker.model.formatTime
import edu.bloomu.bipolarsymptomtracker.ui.theme.Painters
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.Units
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_card
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_card_pressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_card_secondary
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_card_secondary_pressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_primary

/**
 * A formatted card that other components here can use as a template
 *
 * @param modifier Formatting
 * @param elevation How far to elevate card from background
 * @param colors Color profile for the card
 * @param border Border styling for the card
 * @param content The content to display inside the card
 */
@Composable
fun BasicCard(
    modifier: Modifier,
    elevation: CardElevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    colors: CardColors = md_theme_card,
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(24.dp)
    Card (
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        modifier = modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            content()
        }
    }
}

/**
 * A formatted card that allows the user to read about a symptom and log it
 *
 * @param symptom The symptom to display
 * @param modifier Formatting
 */
@Composable
fun SymptomCard(
    symptom: Symptom,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var isSymptomatic by remember { mutableStateOf(symptom.isChecked()) }

    BasicCard(
        colors = if (isSymptomatic) md_theme_card_pressed else md_theme_card,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = isSymptomatic,
                    onCheckedChange = { symptom.toggleChecked(); isSymptomatic = !isSymptomatic },
                    modifier = Modifier
                        .padding(8.dp)
                )
                Text(
                    symptom.getName(),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            IconButton(onClick = { expanded = !expanded }) {
                val icon = if (expanded) Painters.Outlined.DropDown else Painters.Outlined.DropLeft
                Icon(painter = icon, contentDescription = null)
            }
        }

        // Display symptom description
        if (expanded) {
            Text(
                symptom.getDesc(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(
                        vertical = 6.dp,
                        horizontal = 12.dp
                    )
            )
        }
    }
}

/**
 * A stylized card to display Entry information on the Entries Screen
 *
 * @param entry The entry to display
 * @param modifier Formatting
 * @param onDelete Allows parent to delete entry when button is pushed
 * @param onClick Allows navManager to navigate to Edit Entry when card is clicked
 */
@Composable
fun EntryCard(
    entry: Entry,
    modifier: Modifier,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }

    BasicCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            StateIcon(
                state = entry.state,
                modifier = Modifier
                    .size(Units.Icons.ExxtraLarge)
                    .padding(Units.Padding.Icon)
            )
            formatDate(entry.date)?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(
                            horizontal = Units.Padding.CardTitle
                        )
                )
            }
            formatTime(entry.date)?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(
                            horizontal = Units.Padding.CardTitle
                        )
                )
            }
            DeleteButton(
                onClick = { showConfirmDialog = true },
                modifier = Modifier
                    .padding( horizontal = 26.dp, vertical = 6.dp )
                    .align(Alignment.CenterVertically)
            )
            ConfirmDialog(
                isVisible = showConfirmDialog,
                onConfirm = {
                    onDelete()
                    showConfirmDialog = false
                    showSuccessDialog = true
                },
                onCancel = { showConfirmDialog = false },
                confirmButton = Strings.Screens.Entries.Delete.ConfirmDialog.confirmButton,
                cancelButton = Strings.Screens.Entries.Delete.ConfirmDialog.cancelButton,
                message = Strings.Screens.Entries.Delete.ConfirmDialog.message,
                title = Strings.Screens.Entries.Delete.ConfirmDialog.title
            )
            SuccessDialog(
                isVisible = showSuccessDialog,
                onConfirm = { showSuccessDialog = false },
                title = Strings.Screens.Entries.Delete.SuccessDialog.title,
                message = Strings.Screens.Entries.Delete.SuccessDialog.body
            )
        }
    }
}

/**
 * Stylized card with a large footprint and a switch indicator
 *
 * @param onClick Allows parent to update information when card is clicked
 * @param content The content to display
 * @param initiallySelected Whether to put card into 'selected' state
 * @param modifier Formatting
 */
@Composable
fun SwitchCard(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
    initiallySelected: Boolean = false,
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf(initiallySelected) }

    BasicCard(
        elevation = CardDefaults.cardElevation(0.dp),
        colors = if (selected) md_theme_card_secondary_pressed else md_theme_card_secondary,
        border = BorderStroke(8.dp, md_theme_primary),
        modifier = modifier
            .clickable {
                onClick()
                selected = !selected
            }
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
            ) { content() }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
            ) {
                Switch(
                    checked = selected,
                    onCheckedChange = {
                        selected = it // Update the state when toggled
                        onClick()
                    },
                    modifier = Modifier
                )
            }
        }
    }
}

/**
 * A Switch Card for only a text element
 */
@Composable
fun TextSwitchCard(
    onClick: () -> Unit,
    text: String,
    initiallySelected: Boolean = false,
    modifier: Modifier = Modifier
) {
    SwitchCard(
        onClick = onClick,
        initiallySelected = initiallySelected,
        modifier = modifier,
        content = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(end = 8.dp)
            )
        }
    )
}