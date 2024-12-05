package edu.bloomu.bipolarsymptomtracker.model

enum class CyclePosition {
    BEGINNING,
    MIDDLE,
    END,
    UNKNOWN;

    override fun toString(): String {
        return when(this) {
            BEGINNING -> " in the beginning  "
            MIDDLE -> " in the middle  "
            END -> " at the end  "
            UNKNOWN -> " at an unknown point  "
        }
    }
}