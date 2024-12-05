package edu.bloomu.bipolarsymptomtracker.model

import androidx.compose.ui.graphics.Color
import edu.bloomu.bipolarsymptomtracker.ui.AnalysisDisplayValues
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_depressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_hypo_depressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_hypo_manic
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_manic
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_none
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_unstable

enum class State {
    FULL_MANIC,
    HYPO_MANIC,
    FULL_DEPRESSIVE,
    HYPO_DEPRESSIVE,
    NEUTRAL,
    UNKNOWN,
    UNSTABLE;

    /**
     * @return the most closely associated state with this state
     */
    fun getPartner(): State {
        return when(this) {
            FULL_MANIC -> HYPO_MANIC
            HYPO_MANIC -> FULL_MANIC
            FULL_DEPRESSIVE -> FULL_DEPRESSIVE
            HYPO_DEPRESSIVE -> HYPO_DEPRESSIVE
            NEUTRAL -> UNKNOWN
            UNKNOWN -> NEUTRAL
            UNSTABLE -> UNSTABLE
        }
    }

    fun getColor(): Color {
        return when(this) {
            FULL_MANIC -> md_theme_light_state_manic
            HYPO_MANIC -> md_theme_light_state_hypo_manic
            FULL_DEPRESSIVE -> md_theme_light_state_depressed
            HYPO_DEPRESSIVE -> md_theme_light_state_hypo_depressed
            UNSTABLE -> md_theme_light_state_unstable
            else -> md_theme_light_state_none
        }
    }

    fun isEnemy(state: State): Boolean {
        return when(this) {
            FULL_MANIC, HYPO_MANIC -> state.isThisOrPartner(FULL_DEPRESSIVE)
            FULL_DEPRESSIVE, HYPO_DEPRESSIVE -> state.isThisOrPartner(FULL_MANIC)
            UNSTABLE -> state != UNSTABLE
            else -> false
        }
    }

    /**
     * @return whether the state is a hypo state
     */
    fun isHypo(): Boolean {
        return when(this) {
            HYPO_MANIC -> true
            HYPO_DEPRESSIVE -> true
            NEUTRAL -> true
            else -> false
        }
    }

    /**
     * @return whether the state is a full state
     */
    fun isFull(): Boolean {
        return !this.isHypo()
    }

    fun isUnknownOrNeutral(): Boolean { return this == UNKNOWN || this == NEUTRAL }
    fun isUnstable(): Boolean { return this == UNSTABLE }

    fun isThisOrPartner(state: State): Boolean {
        return state == this || state == this.getPartner()
    }

    fun getDisplayValues(): AnalysisDisplayValues {
        return when(this) {
            FULL_MANIC ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.StateText.Manic.Title,
                    blurb = Strings.StateText.Manic.Desc,
                    body = Strings.StateText.Manic.Body
                )
            HYPO_MANIC ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.StateText.HypoManic.Title,
                    blurb = Strings.StateText.HypoManic.Desc,
                    body = Strings.StateText.HypoManic.Body
                )
            FULL_DEPRESSIVE ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.StateText.Depressed.Title,
                    blurb = Strings.StateText.Depressed.Desc,
                    body = Strings.StateText.Depressed.Body
                )
            HYPO_DEPRESSIVE ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.StateText.HypoDepressed.Title,
                    blurb = Strings.StateText.HypoDepressed.Desc,
                    body = Strings.StateText.HypoDepressed.Body
                )
            NEUTRAL ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.StateText.Neutral.Title,
                    blurb = Strings.StateText.Neutral.Desc,
                    body = Strings.StateText.Neutral.Body
                )
            UNKNOWN ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.StateText.Unknown.Title,
                    blurb = Strings.StateText.Unknown.Desc,
                    body = Strings.StateText.Unknown.Body
                )
            UNSTABLE ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.StateText.Unstable.Title,
                    blurb = Strings.StateText.Unstable.Desc,
                    body = Strings.StateText.Unstable.Body
                )
        }
    }
}