package edu.bloomu.bipolarsymptomtracker.model

import android.content.Context
import androidx.compose.runtime.Composable
import edu.bloomu.bipolarsymptomtracker.db.Entry

@Composable
fun AnalyzeMe(
    context: Context,
    entries: List<State>
) : StateAnalysis {
    val recentStates = entries
    if (recentStates.isEmpty()) return StateAnalysis()
    val cycleLength = 14
        //getCycleLength(context)

    val streaks: MutableList<Int> = mutableListOf(0)
    val statesInStreaks: MutableList<State> = mutableListOf(recentStates[0])
    val isUnknownOrNeutral: MutableList<Boolean> = mutableListOf(recentStates[0] == State.UNKNOWN || recentStates[0] == State.NEUTRAL)
    val isUnstable: MutableList<Boolean> = mutableListOf(recentStates[0] == State.UNSTABLE)

    var currentPartner = when(statesInStreaks[0]) {
        State.MANIC -> State.HYPO_MANIC
        State.HYPO_MANIC -> State.MANIC
        State.DEPRESSIVE -> State.HYPO_DEPRESSIVE
        State.HYPO_DEPRESSIVE -> State.DEPRESSIVE
        State.NEUTRAL -> State.UNKNOWN
        State.UNKNOWN -> State.NEUTRAL
        State.UNSTABLE -> State.UNSTABLE
    }

    var mainCount = 0
    var hypoCount = 0

    var i = 0

    for (state in recentStates) {
        if (state == statesInStreaks[i] || state == currentPartner ) {
            streaks[i]++

            if (state == State.HYPO_MANIC
                || state == State.HYPO_DEPRESSIVE
                || state == State.NEUTRAL) hypoCount++
            else mainCount++
        }
        else {
            val isHypo = mainCount < (0.6 * hypoCount)

            when (statesInStreaks[i]) {
                State.MANIC -> if (isHypo) statesInStreaks[i] = State.HYPO_MANIC
                State.DEPRESSIVE -> if (isHypo) statesInStreaks[i] = State.HYPO_DEPRESSIVE
                State.HYPO_MANIC -> if (!isHypo) statesInStreaks[i] = State.MANIC
                State.HYPO_DEPRESSIVE -> if (!isHypo) statesInStreaks[i] = State.DEPRESSIVE
                State.UNKNOWN -> if (isHypo) statesInStreaks[i] = State.NEUTRAL
                State.NEUTRAL -> if (!isHypo) statesInStreaks[i] = State.UNKNOWN
                else -> {}
            }

            streaks.add(element = 0)
            statesInStreaks.add(element = state)
            isUnknownOrNeutral.add(element = state == State.UNKNOWN || state == State.NEUTRAL)
            isUnstable.add(element = state == State.UNSTABLE)

            currentPartner = when(statesInStreaks[0]) {
                State.MANIC -> State.HYPO_MANIC
                State.HYPO_MANIC -> State.MANIC
                State.DEPRESSIVE -> State.HYPO_DEPRESSIVE
                State.HYPO_DEPRESSIVE -> State.DEPRESSIVE
                State.NEUTRAL -> State.UNKNOWN
                State.UNKNOWN -> State.NEUTRAL
                State.UNSTABLE -> State.UNSTABLE
            }

            mainCount = 0
            hypoCount = 0

            i++
        }
    }

    var averageStreakLength = 0

    var j = streaks.size - 1
    var d = 1

    for (len in streaks.reversed()) {
        if (!isUnknownOrNeutral[j] && !isUnstable[j]) d++
        averageStreakLength += len
        j--
    }
    averageStreakLength /= d

    val currentStartingIndex = isUnknownOrNeutral.indexOfFirst { false }

    var numberOfUnstables = 0
    for (entry in isUnstable) if (entry) numberOfUnstables++

    val isCurrentlyUnstable = (isUnstable[0] && streaks[0] > 3) || averageStreakLength < 4 || numberOfUnstables > (0.4 * streaks.size)

    var stateAnalysis = State.UNKNOWN
    if (recentStates.size < 5)  { stateAnalysis = State.UNKNOWN }
    else if (isCurrentlyUnstable) { stateAnalysis = State.UNSTABLE }
    else if (isUnknownOrNeutral[0] && streaks[0] > (0.6 * averageStreakLength)) { stateAnalysis = statesInStreaks[0] }
    else {
        for (entry in statesInStreaks) {
            if (entry != State.NEUTRAL && entry != State.UNKNOWN && entry != State.UNSTABLE) {
                stateAnalysis = entry
            }
        }
    }

    val cyclePosition: StateStatus

    var currentStreakLength = 0
    for (k in 0 until currentStartingIndex + 1) currentStreakLength += streaks[k]

    if (recentStates.size < cycleLength) cyclePosition = StateStatus.UNKNOWN
    else if (currentStreakLength < (cycleLength / 3)) cyclePosition = StateStatus.BEGINNING
    else if (currentStreakLength > (2 * cycleLength / 3)) cyclePosition = StateStatus.END
    else cyclePosition = StateStatus.MIDDLE

    //if (recentStates.size >= 21) updateCycleLength(averageStreakLength, context)

    return StateAnalysis(
        streakLength = currentStreakLength,
        stateAnalysis = stateAnalysis,
        stateStatus = cyclePosition
    )
}

fun getCycleLength(
    context: Context
) : Int {
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    return sharedPreferences.getInt("cycle_length", 14)
}

fun updateCycleLength(
    newLength: Int,
    context: Context
) {
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    val cycleLength = sharedPreferences.getInt("cycle_length", 14)
    val editor = sharedPreferences.edit()
    editor.putInt("cycle_length", cycleLength + newLength / 2)
    editor.apply()
}

fun collectRecentStates(
    entries: List<Entry>
) : List<State> {
    val recentStates: MutableList<State> = mutableListOf()

    for (entry in entries.reversed()) {
        recentStates.add(entry.analysis)
        if (recentStates.size == 35) break
    }

    return recentStates.toList()
}