package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import edu.bloomu.bipolarsymptomtracker.model.State
import edu.bloomu.bipolarsymptomtracker.ui.theme.Icons

@Composable
fun AnalysisCalendar(
    recentStates: List<State>
) {
    Row {
        for (state in recentStates) {
            CalendarElement(state)
        }
    }
}

@Composable
fun CalendarElement(
    state: State
) {
    val painter: Painter
    when(state) {
        State.MANIC -> painter = Icons.Outlined.AnalysisManic
        State.HYPO_MANIC -> painter = Icons.Outlined.AnalysisHypoManic
        State.DEPRESSIVE -> painter = Icons.Outlined.AnalysisDepressive
        State.HYPO_DEPRESSIVE -> painter = Icons.Outlined.AnalysisHypoDepressive
        State.UNSTABLE -> painter = Icons.Outlined.AnalysisUnstable
        else -> painter = Icons.Outlined.AnalysisNone
    }
    Box {
        Image(
            painter = Icons.Outlined.CalendarDay,
            contentDescription = ""
        )
        Image(
            painter = painter,
            contentDescription = ""
        )
    }
}