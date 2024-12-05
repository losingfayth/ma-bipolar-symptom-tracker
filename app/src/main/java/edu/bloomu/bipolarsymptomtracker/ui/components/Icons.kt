package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import edu.bloomu.bipolarsymptomtracker.model.State
import edu.bloomu.bipolarsymptomtracker.ui.theme.Painters
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_depressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_manic
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_none
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_unstable

@Composable fun StateAnalysisManic(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisManic,
        contentDescription = Strings.StateText.Manic.ImageDesc,
        tint = if(color) md_theme_light_state_manic else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}
@Composable fun StateAnalysisHypoManic(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisHypoManic,
        contentDescription = Strings.StateText.HypoManic.ImageDesc,
        tint = if (color) md_theme_light_state_manic else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}
@Composable fun StateAnalysisDepressive(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisDepressive,
        contentDescription = Strings.StateText.Depressed.ImageDesc,
        tint = if (color) md_theme_light_state_depressed else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}
@Composable fun StateAnalysisHypoDepressive(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisHypoDepressive,
        contentDescription = Strings.StateText.HypoDepressed.ImageDesc,
        tint = if (color) md_theme_light_state_depressed else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}
@Composable fun StateAnalysisUnknown(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisNone,
        contentDescription = Strings.StateText.Unknown.ImageDesc,
        tint = if (color) md_theme_light_state_none else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}
@Composable fun StateAnalysisUnstable(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisUnstable,
        contentDescription = Strings.StateText.Unstable.ImageDesc,
        tint = if (color) md_theme_light_state_unstable else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}
@Composable fun StateAnalysisNeutral(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisNone,
        contentDescription = Strings.StateText.Neutral.ImageDesc,
        tint = if (color) md_theme_light_state_none else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}

@Composable fun StateAnalysisIcon(
    state: State,
    color: Boolean = true,
    modifier: Modifier = Modifier
) {
    return when(state) {
        State.MANIC -> StateAnalysisManic(modifier, color)
        State.HYPO_MANIC -> StateAnalysisHypoManic(modifier, color)
        State.DEPRESSIVE -> StateAnalysisDepressive(modifier, color)
        State.HYPO_DEPRESSIVE -> StateAnalysisHypoDepressive(modifier, color)
        State.NEUTRAL -> StateAnalysisNeutral(modifier, color)
        State.UNKNOWN -> StateAnalysisUnknown(modifier, color)
        State.UNSTABLE -> StateAnalysisUnstable(modifier, color)
    }
}