package edu.bloomu.bipolarsymptomtracker.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import edu.bloomu.bipolarsymptomtracker.R

object Icons {
    object Outlined {
        val Home: Painter
            @Composable get() = painterResource(id = R.drawable.ic_home)
        val Edit: Painter
            @Composable get() = painterResource(id = R.drawable.ic_edit_square)
        val Save: Painter
            @Composable get() = painterResource(id = R.drawable.ic_save)
        val New: Painter
            @Composable get() = painterResource(id = R.drawable.ic_add)
        val Exit: Painter
            @Composable get() = painterResource(id = R.drawable.ic_close)
        val DropDown: Painter
            @Composable get() = painterResource(id = R.drawable.ic_arrow_drop_down)
        val DropLeft: Painter
            @Composable get() = painterResource(id = R.drawable.ic_arrow_left)
        val DropUp: Painter
            @Composable get() = painterResource(id = R.drawable.ic_arrow_drop_up)
        val MoodVLow: Painter
            @Composable get() = painterResource(id = R.drawable.ic_sentiment_sad)
        val MoodLow: Painter
            @Composable get() = painterResource(id = R.drawable.ic_sentiment_dissatisfied)
        val MoodNeutral: Painter
            @Composable get() = painterResource(id = R.drawable.ic_sentiment_neutral)
        val MoodHigh: Painter
            @Composable get() = painterResource(id = R.drawable.ic_sentiment_satisfied)
        val MoodVHigh: Painter
            @Composable get() = painterResource(id = R.drawable.ic_sentiment_excited)
        val Calendar: Painter
            @Composable get() = painterResource(id = R.drawable.ic_calendar_month)
        val Entry: Painter
            @Composable get() = painterResource(id = R.drawable.ic_article)
        val List: Painter
            @Composable get() = painterResource(id = R.drawable.ic_list_alt)
        val Settings: Painter
            @Composable get() = painterResource(id = R.drawable.ic_settings)
        val Analytics: Painter
            @Composable get() = painterResource(id = R.drawable.ic_analytics)
        val AnalysisManic: Painter
            @Composable get() = painterResource(id = R.drawable.ic_sentiment_very_satisfied)
        val AnalysisDepressive: Painter
            @Composable get() = painterResource(id = R.drawable.ic_sentiment_sad)
        val AnalysisUnstable: Painter
            @Composable get() = painterResource(id = R.drawable.ic_sentiment_neutral)
        val AnalysisHypoManic: Painter
            @Composable get() = painterResource(id = R.drawable.ic_sentiment_dissatisfied)
        val AnalysisHypoDepressive: Painter
            @Composable get() = painterResource(id = R.drawable.ic_sentiment_dissatisfied)
        val AnalysisNone: Painter
            @Composable get() = painterResource(id = R.drawable.ic_sentiment_neutral)
        val CalendarDay: Painter
            @Composable get() = painterResource(id = R.drawable.ic_calendar_today)
    }
}