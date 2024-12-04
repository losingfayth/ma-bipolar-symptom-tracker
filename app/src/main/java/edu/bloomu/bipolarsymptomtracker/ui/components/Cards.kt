package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.R
import edu.bloomu.bipolarsymptomtracker.db.Entry
import edu.bloomu.bipolarsymptomtracker.model.State
import edu.bloomu.bipolarsymptomtracker.model.Symptom
import edu.bloomu.bipolarsymptomtracker.ui.theme.Icons

@Composable
fun BasicCard(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(24.dp)
    Card (
        shape = shape,
        colors = CardDefaults.cardColors(),
        modifier = modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            //horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center
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

    BasicCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
    {
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
                    checked = symptom.isSymptomatic(),
                    onCheckedChange = { symptom.toggleSymptomatic() }
                )
                Text(
                    symptom.getName(),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Icon Button
            IconButton(onClick = { expanded = !expanded }) {
                val icon = if (expanded) R.drawable.arrow_drop_down_24px else R.drawable.arrow_left_24px
                Icon(painter = painterResource(id = icon), contentDescription = null)
            }
        }

        // Description (Expanded Content)
        if (expanded) {
            Text(
                symptom.getDesc(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
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
        modifier = modifier
            .clickable { onClick() },
    ) {
        Icon(
            painter = when (entry.analysis) {
                State.MANIC -> Icons.Outlined.MoodVHigh
                State.HYPO_MANIC -> Icons.Outlined.MoodVHigh
                State.DEPRESSIVE -> Icons.Outlined.MoodVLow
                State.HYPO_DEPRESSIVE -> Icons.Outlined.MoodVLow
                else -> Icons.Outlined.MoodNeutral
            },
            contentDescription = "",
            tint = MaterialTheme.colorScheme.error
        )
        Text(
            text = entry.date,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}