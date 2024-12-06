package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.R
import edu.bloomu.bipolarsymptomtracker.model.MoodGroup
import edu.bloomu.bipolarsymptomtracker.model.MoodLevel
import edu.bloomu.bipolarsymptomtracker.ui.theme.Painters
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_accent
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_depressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_hypo_depressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_hypo_manic
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_manic

/**
 * Creates an interactable group of icons for the user to log their moods
 *
 * @param moodGroup The Mood Group to display, for the user to interact with
 */
@Composable
fun MoodDial(moodGroup: MoodGroup) {
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
        // Element for each loggable mood
        MoodDialElement(
            Painters.Outlined.MoodVLow,
            stringResource(id = R.string.md_ref_mood_v_low_desc),
            onClick = { moodGroup.toggleMood(MoodLevel.VERY_LOW) },
            initiallySelected = moodGroup.getMood(MoodLevel.VERY_LOW),
            moodColor = md_theme_state_depressed,
            modifier = Modifier
                .size(iconSize)
        )
        MoodDialElement(
            Painters.Outlined.MoodLow,
            stringResource(id = R.string.md_ref_mood_low_desc),
            onClick = { moodGroup.toggleMood(MoodLevel.LOW) },
            initiallySelected = moodGroup.getMood(MoodLevel.LOW),
            moodColor = md_theme_state_hypo_depressed,
            modifier = Modifier
                .size(iconSize)
        )
        MoodDialElement(
            Painters.Outlined.MoodNeutral,
            stringResource(id = R.string.md_ref_mood_neutral_desc),
            onClick = { moodGroup.toggleMood(MoodLevel.NEUTRAL) },
            initiallySelected = moodGroup.getMood(MoodLevel.NEUTRAL),
            moodColor = md_theme_accent,
            modifier = Modifier
                .size(iconSize)
        )
        MoodDialElement(
            Painters.Outlined.MoodHigh,
            stringResource(id = R.string.md_ref_mood_high_desc),
            onClick = { moodGroup.toggleMood(MoodLevel.HIGH) },
            initiallySelected = moodGroup.getMood(MoodLevel.HIGH),
            moodColor = md_theme_state_hypo_manic,
            modifier = Modifier
                .size(iconSize)
        )
        MoodDialElement(
            Painters.Outlined.MoodVHigh,
            stringResource(id = R.string.md_ref_mood_v_high_desc),
            onClick = { moodGroup.toggleMood(MoodLevel.VERY_HIGH) },
            initiallySelected = moodGroup.getMood(MoodLevel.VERY_HIGH),
            moodColor = md_theme_state_manic,
            modifier = Modifier
                .size(iconSize)
        )
    }
}

/**
 * An element of a Mood Dial for displaying a single mood in a Mood Group
 *
 * @param icon The icon associated with this mood
 * @param desc The alt text for the icon
 * @param initiallySelected Whether to start the element selected
 * @param onClick Allows the parent to log when child is clicked
 * @param moodColor The color to display the mood in
 * @param modifier Formatting
 */
@Composable
fun MoodDialElement(
    icon: Painter,
    desc: String,
    initiallySelected: Boolean,
    onClick: () -> Unit,
    moodColor: Color,
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
                if (selected) moodColor
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