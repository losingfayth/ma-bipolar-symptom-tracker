package edu.bloomu.bipolarsymptomtracker.model

class Mood(vLow: Boolean, low: Boolean, neutral: Boolean, high: Boolean, vHigh: Boolean) {
    private var vLow = vLow;
    private var low = low;
    private var neutral = neutral;
    private var high = high;
    private var vHigh = vHigh;

    constructor() : this(false, false, false, false, false)

    fun setMood(mood: MoodLevel, bool: Boolean) {
        when(mood) {
            MoodLevel.VERY_LOW -> vLow = bool
            MoodLevel.LOW -> low = bool
            MoodLevel.NEUTRAL -> neutral = bool
            MoodLevel.HIGH -> high = bool
            MoodLevel.VERY_HIGH -> vHigh = bool
        }
    }

    fun toggleMood(mood: MoodLevel) {
        when(mood) {
            MoodLevel.VERY_LOW -> vLow = !vLow
            MoodLevel.LOW -> low = !low
            MoodLevel.NEUTRAL -> neutral = !neutral
            MoodLevel.HIGH -> high = !high
            MoodLevel.VERY_HIGH -> vHigh = !vHigh
        }
    }

    fun getMood(mood: MoodLevel): Boolean {
        return when(mood) {
            MoodLevel.VERY_LOW -> vLow
            MoodLevel.LOW -> low
            MoodLevel.NEUTRAL -> neutral
            MoodLevel.HIGH -> high
            MoodLevel.VERY_HIGH -> vHigh
        }
    }
}