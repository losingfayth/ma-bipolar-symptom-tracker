package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import edu.bloomu.bipolarsymptomtracker.R

@Composable
fun MoodDial() {
    Row {
        Column {
            MoodDialElement(
                painterResource(id = R.drawable.sentiment_sad_24px),
                stringResource(id = R.string.md_ref_mood_v_low_desc)
            )
        }
        Column {
            MoodDialElement(
                painterResource(id = R.drawable.sentiment_dissatisfied_24px),
                stringResource(id = R.string.md_ref_mood_low_desc)
            )
        }
        Column {
            MoodDialElement(
                painterResource(id = R.drawable.sentiment_neutral_24px),
                stringResource(id = R.string.md_ref_mood_neutral_desc)
            )
        }
        Column {
            MoodDialElement(
                painterResource(id = R.drawable.sentiment_satisfied_24px),
                stringResource(id = R.string.md_ref_mood_high_desc)
            )
        }
        Column {
            MoodDialElement(
                painterResource(id = R.drawable.sentiment_very_satisfied_24px),
                stringResource(id = R.string.md_ref_mood_v_high_desc)
            )
        }
    }
}

@Composable
fun MoodDialElement(
    icon: Painter,
    desc: String
) {
    Row { // this should be icon (icon only)
        Image(icon, desc)
    }
}