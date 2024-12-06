package edu.bloomu.bipolarsymptomtracker.model

import androidx.compose.ui.graphics.Color
import edu.bloomu.bipolarsymptomtracker.ui.AnalysisDisplayValues
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_depressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_hypo_depressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_hypo_manic
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_manic
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_none
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_state_unstable

/**
 * A class representing the various Bipolar states the user could be in
 *
 * Most of the functions of this class are quality of life functions to make some code elsewhere
 * just the tiniest bit more legible
 */
enum class State {
    FULL_MANIA,         // User is very manic
    HYPO_MANIA,         // User is mildly manic
    FULL_DEPRESSIVE,    // User is very depressed
    HYPO_DEPRESSIVE,    // User is very depressed
    NEUTRAL,            // User is emotionally stable
    UNKNOWN,            // User's state cannot be determined
    UNSTABLE;           // User is emotionally unstable

    /**
     * @return the most closely associated state with this state so Cycles can be accurately
     *      calculated
     */
    private fun getPartner(): State {
        return when(this) {
            FULL_MANIA -> HYPO_MANIA
            HYPO_MANIA -> FULL_MANIA
            FULL_DEPRESSIVE -> FULL_DEPRESSIVE
            HYPO_DEPRESSIVE -> HYPO_DEPRESSIVE
            NEUTRAL -> UNKNOWN
            UNKNOWN -> NEUTRAL
            UNSTABLE -> UNSTABLE
        }
    }

    /**
     * @return whether this state is a hypo state
     */
    fun isHypo(): Boolean {
        return when(this) {
            HYPO_MANIA -> true
            HYPO_DEPRESSIVE -> true
            NEUTRAL -> true
            else -> false
        }
    }

    /**
     * @return whether this state is a full state
     */
    fun isFull(): Boolean { return !this.isHypo() }

    /**
     * @return whether this state is is an Unknown state or a Neutral state
     */
    fun isUnknownOrNeutral(): Boolean { return this == UNKNOWN || this == NEUTRAL }

    /**
     * @return whether this state is an Unstable state
     */
    fun isUnstable(): Boolean { return this == UNSTABLE }

    /**
     * @param state A state to be checked for sameness
     * @return whether the given state is the same or similar to this state
     */
    fun isThisOrPartner(state: State): Boolean { return state == this || state == this.getPartner() }

    /**
     * @param state A state to be checked for opposite-ness
     * @return whether the given state is the opposite of this state
     */
    fun isEnemy(state: State): Boolean {
        return when(this) {
            FULL_MANIA, HYPO_MANIA -> state.isThisOrPartner(FULL_DEPRESSIVE)
            FULL_DEPRESSIVE, HYPO_DEPRESSIVE -> state.isThisOrPartner(FULL_MANIA)
            UNSTABLE -> state != UNSTABLE
            else -> false
        }
    }

    /**
     * @return the theme color associated with this state
     */
    private fun getColor(): Color {
        return when(this) {
            FULL_MANIA -> md_theme_state_manic
            HYPO_MANIA -> md_theme_state_hypo_manic
            FULL_DEPRESSIVE -> md_theme_state_depressed
            HYPO_DEPRESSIVE -> md_theme_state_hypo_depressed
            UNSTABLE -> md_theme_state_unstable
            else -> md_theme_state_none
        }
    }

    /**
     * @return the associated values to be displayed on the Analysis screen
     */
    fun getDisplayValues(): AnalysisDisplayValues {
        return when(this) {
            FULL_MANIA ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.Model.States.Manic.title,
                    blurb = Strings.Model.States.Manic.blurb,
                    body = Strings.Model.States.Manic.body
                )
            HYPO_MANIA ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.Model.States.HypoManic.title,
                    blurb = Strings.Model.States.HypoManic.blurb,
                    body = Strings.Model.States.HypoManic.body
                )
            FULL_DEPRESSIVE ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.Model.States.Depressed.title,
                    blurb = Strings.Model.States.Depressed.blurb,
                    body = Strings.Model.States.Depressed.body
                )
            HYPO_DEPRESSIVE ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.Model.States.HypoDepressed.title,
                    blurb = Strings.Model.States.HypoDepressed.blurb,
                    body = Strings.Model.States.HypoDepressed.body
                )
            NEUTRAL ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.Model.States.Neutral.title,
                    blurb = Strings.Model.States.Neutral.blurb,
                    body = Strings.Model.States.Neutral.body
                )
            UNKNOWN ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.Model.States.Unknown.title,
                    blurb = Strings.Model.States.Unknown.blurb,
                    body = Strings.Model.States.Unknown.body
                )
            UNSTABLE ->
                AnalysisDisplayValues(
                    color = this.getColor(),
                    title = Strings.Model.States.Unstable.title,
                    blurb = Strings.Model.States.Unstable.blurb,
                    body = Strings.Model.States.Unstable.body
                )
        }
    }
}