package edu.bloomu.bipolarsymptomtracker.model

import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings

/**
 * An indicator for where the user currently is in their cycle
 */
enum class CyclePosition {
    START,
    MIDDLE,
    END,
    UNKNOWN;

    /**
     * @return a display string to use on the Analysis screen
     */
    override fun toString(): String {
        return when(this) {
            START -> Strings.Model.CyclePositions.start
            MIDDLE -> Strings.Model.CyclePositions.middle
            END -> Strings.Model.CyclePositions.end
            UNKNOWN -> Strings.Model.CyclePositions.unknown
        }
    }
}