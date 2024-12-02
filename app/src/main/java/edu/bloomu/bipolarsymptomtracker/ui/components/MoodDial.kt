package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.R
import edu.bloomu.bipolarsymptomtracker.model.Mood
import edu.bloomu.bipolarsymptomtracker.model.MoodLevel
import edu.bloomu.bipolarsymptomtracker.ui.theme.Icons

@Composable
fun MoodDial(
    mood: Mood
) {
    val configuration = LocalConfiguration.current
    val iconSize = (configuration.screenWidthDp.dp) / 6

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(6.dp)
    ) {
        MoodDialElement(
            Icons.Outlined.MoodVLow,
            stringResource(id = R.string.md_ref_mood_v_low_desc),
            onClick = { mood.toggleMood(MoodLevel.VERY_LOW) },
            initiallySelected = mood.getMood(MoodLevel.VERY_LOW),
            modifier = Modifier
                .size(iconSize)
        )
        MoodDialElement(
            Icons.Outlined.MoodLow,
            stringResource(id = R.string.md_ref_mood_low_desc),
            onClick = { mood.toggleMood(MoodLevel.LOW) },
            initiallySelected = mood.getMood(MoodLevel.LOW),
            modifier = Modifier
                .size(iconSize)
        )
        MoodDialElement(
            Icons.Outlined.MoodNeutral,
            stringResource(id = R.string.md_ref_mood_neutral_desc),
            onClick = { mood.toggleMood(MoodLevel.NEUTRAL) },
            initiallySelected = mood.getMood(MoodLevel.NEUTRAL),
            modifier = Modifier
                .size(iconSize)
        )
        MoodDialElement(
            Icons.Outlined.MoodHigh,
            stringResource(id = R.string.md_ref_mood_high_desc),
            onClick = { mood.toggleMood(MoodLevel.HIGH) },
            initiallySelected = mood.getMood(MoodLevel.HIGH),
            modifier = Modifier
                .size(iconSize)
        )
        MoodDialElement(
            Icons.Outlined.MoodVHigh,
            stringResource(id = R.string.md_ref_mood_v_high_desc),
            onClick = { mood.toggleMood(MoodLevel.VERY_HIGH) },
            initiallySelected = mood.getMood(MoodLevel.VERY_HIGH),
            modifier = Modifier
                .size(iconSize)
        )
    }
}

@Composable
fun MoodDialElement(
    icon: Painter,
    desc: String,
    initiallySelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    var selected by remember { mutableStateOf(initiallySelected) }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        IconButton(
            onClick = { onClick(); selected = !selected; },
            modifier = modifier
        ) {
            val color =
                if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            Icon(
                painter = icon,
                contentDescription = desc,
                tint = color,
                modifier = modifier
            )
        }
    }
}