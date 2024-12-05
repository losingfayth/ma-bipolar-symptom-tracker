package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import edu.bloomu.bipolarsymptomtracker.ui.theme.Units
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_card_dark
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_card_light

@Composable
fun BasicCard(
    modifier: Modifier,
    elevation: CardElevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    colors: CardColors = md_theme_light_card_light,
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

@Composable
fun SymptomCard(
    symptom: Symptom,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var isSymptomatic by remember { mutableStateOf(symptom.isChecked()) }

    BasicCard(
        colors = if (isSymptomatic) md_theme_light_card_dark else md_theme_light_card_light,
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
            // Checkbox and Name
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

            // Icon Button
            IconButton(onClick = { expanded = !expanded }) {
                val icon = if (expanded) Painters.Outlined.DropDown else Painters.Outlined.DropLeft
                Icon(painter = icon, contentDescription = null)
            }
        }

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

@Composable
fun EntryCard(
    entry: Entry,
    modifier: Modifier,
    onClick: () -> Unit
) {
    BasicCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            StateAnalysisIcon(
                state = entry.analysis,
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
        }
    }
}