package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import edu.bloomu.bipolarsymptomtracker.model.State
import edu.bloomu.bipolarsymptomtracker.ui.theme.Painters
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_depressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_manic
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_none
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_unstable

/**
 * @param modifier Formatting
 * @param color Color (true) or greyscale (false)
 * @return a custom Icon
 */
@Composable fun StateAnalysisManic(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisManic,
        contentDescription = Strings.Model.States.Manic.imageAlt,
        tint = if(color) md_theme_state_manic else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}

/**
 * @param modifier Formatting
 * @param color Color (true) or greyscale (false)
 * @return a custom Icon
 */
@Composable fun StateAnalysisHypoManic(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisHypoManic,
        contentDescription = Strings.Model.States.HypoManic.imageAlt,
        tint = if (color) md_theme_state_manic else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}

/**
 * @param modifier Formatting
 * @param color Color (true) or greyscale (false)
 * @return a custom Icon
 */
@Composable fun StateAnalysisDepressive(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisDepressive,
        contentDescription = Strings.Model.States.Depressed.imageAlt,
        tint = if (color) md_theme_state_depressed else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}

/**
 * @param modifier Formatting
 * @param color Color (true) or greyscale (false)
 * @return a custom Icon
 */
@Composable fun StateAnalysisHypoDepressive(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisHypoDepressive,
        contentDescription = Strings.Model.States.HypoDepressed.imageAlt,
        tint = if (color) md_theme_state_depressed else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}

/**
 * @param modifier Formatting
 * @param color Color (true) or greyscale (false)
 * @return a custom Icon
 */
@Composable fun StateAnalysisUnknown(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisNone,
        contentDescription = Strings.Model.States.Unknown.imageAlt,
        tint = if (color) md_theme_state_none else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}

/**
 * @param modifier Formatting
 * @param color Color (true) or greyscale (false)
 * @return a custom Icon
 */
@Composable fun StateAnalysisUnstable(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisUnstable,
        contentDescription = Strings.Model.States.Unstable.imageAlt,
        tint = if (color) md_theme_state_unstable else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}

/**
 * @param modifier Formatting
 * @param color Color (true) or greyscale (false)
 * @return a custom Icon
 */
@Composable fun StateAnalysisNeutral(modifier: Modifier, color: Boolean) {
    Icon(
        painter = Painters.Filled.AnalysisNone,
        contentDescription = Strings.Model.States.Neutral.imageAlt,
        tint = if (color) md_theme_state_none else MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}

/**
 * @param state The state to get the icon for
 * @param color Whether to display the icon in color (true) or grayscale (false)
 * @param modifier Formatting
 * @return an Icon associated with that State
 */
@Composable fun StateIcon(
    state: State,
    color: Boolean = true,
    modifier: Modifier = Modifier
) {
    return when(state) {
        State.FULL_MANIA -> StateAnalysisManic(modifier, color)
        State.HYPO_MANIA -> StateAnalysisHypoManic(modifier, color)
        State.FULL_DEPRESSIVE -> StateAnalysisDepressive(modifier, color)
        State.HYPO_DEPRESSIVE -> StateAnalysisHypoDepressive(modifier, color)
        State.NEUTRAL -> StateAnalysisNeutral(modifier, color)
        State.UNKNOWN -> StateAnalysisUnknown(modifier, color)
        State.UNSTABLE -> StateAnalysisUnstable(modifier, color)
    }
}