package edu.bloomu.bipolarsymptomtracker.model

/**
 * Stores the user's moods as a collection since only allowing the user to select one mood
 * could restrict the user into choosing a mood they felt only some of the time this entry period
 *
 * @param vLow Whether the user's mood has been very low
 * @param low Whether the user's mood has been low
 * @param neutral Whether the user's mood has been moderate
 * @param high Whether the user's mood has been high
 * @param vHigh Whether the user's mood has been very high
 */
class MoodGroup(
    private var vLow: Boolean,
    private var low: Boolean,
    private var neutral: Boolean,
    private var high: Boolean,
    private var vHigh: Boolean
) {

    /**
     * Constructs an empty mood object to be added to a new entry
     */
    constructor() : this(false, false, false, false, false)


    /**
     * Sets a specific mood level to a specific value
     *
     * @param mood The mood level to change
     * @param bool Whether the user has felt this was this entry
     */
    fun setMood(mood: MoodLevel, bool: Boolean) {
        when(mood) {
            MoodLevel.VERY_LOW -> vLow = bool
            MoodLevel.LOW -> low = bool
            MoodLevel.NEUTRAL -> neutral = bool
            MoodLevel.HIGH -> high = bool
            MoodLevel.VERY_HIGH -> vHigh = bool
        }
    }

    /**
     * @param mood The mood level to toggle
     */
    fun toggleMood(mood: MoodLevel) {
        when(mood) {
            MoodLevel.VERY_LOW -> vLow = !vLow
            MoodLevel.LOW -> low = !low
            MoodLevel.NEUTRAL -> neutral = !neutral
            MoodLevel.HIGH -> high = !high
            MoodLevel.VERY_HIGH -> vHigh = !vHigh
        }
    }

    /**
     * @param mood The mood level to get the state for
     */
    fun getMood(mood: MoodLevel): Boolean
    {
        return when(mood) {
            MoodLevel.VERY_LOW -> vLow
            MoodLevel.LOW -> low
            MoodLevel.NEUTRAL -> neutral
            MoodLevel.HIGH -> high
            MoodLevel.VERY_HIGH -> vHigh
        }
    }

    /**
     * @return a list representing the moods of the group
     */
    fun getMoods(): List<Boolean> { return listOf(vLow, low, neutral, high, vHigh) }
}

/**
 * The mood levels that are tracked
 */
enum class MoodLevel {
    VERY_LOW,
    LOW,
    NEUTRAL,
    HIGH,
    VERY_HIGH
}