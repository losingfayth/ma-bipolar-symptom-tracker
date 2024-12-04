package edu.bloomu.bipolarsymptomtracker.ui

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.model.State
import edu.bloomu.bipolarsymptomtracker.model.StateAnalysis
import edu.bloomu.bipolarsymptomtracker.model.StateStatus

@Composable
fun Analysis(
    viewModel: EntryViewModel
) {
    val entries by viewModel.entries.collectAsState()

    val recentStates: MutableList<State> = mutableListOf()

    for (entry in entries.reversed()) {
        recentStates.add(entry.analysis)
        if (recentStates.size == 35) break
    }

    val currentAnalysis = analyze(
        context = LocalContext.current,
        entries = recentStates
    )

    val testMe = currentAnalysis.streakLength.toString()

    // Sections
    // 1. Hello, user!
    // 2. Entry map
    // 3. Analysis
    // 4. What to do

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(8.dp) // Additional padding for the content
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text("Howdy, user!")
        }
        Row(

        ) {
            Text(text = testMe)
        }
        Row(

        ) {
            // You're manic
        }
    }
}

@Composable
fun analyze(
    context: Context,
    entries: List<State>
) : StateAnalysis {
    if (entries.isEmpty()) return StateAnalysis()
    val cycleLength = 14
    //getCycleLength(context)

    val streaks: MutableList<Int> = mutableListOf(0)
    val statesInStreaks: MutableList<State> = mutableListOf(entries[0])
    val isUnknownOrNeutral: MutableList<Boolean> =
        mutableListOf(entries[0] == State.UNKNOWN || entries[0] == State.NEUTRAL)
    val isUnstable: MutableList<Boolean> = mutableListOf(entries[0] == State.UNSTABLE)

    var currentPartner = when (statesInStreaks[0]) {
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

    for (state in entries) {
        if (state == statesInStreaks[i] || state == currentPartner) {
            streaks[i]++

            if (state == State.HYPO_MANIC
                || state == State.HYPO_DEPRESSIVE
                || state == State.NEUTRAL
            ) hypoCount++
            else mainCount++
        } else {
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

            currentPartner = when (statesInStreaks[0]) {
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

    val currentStartingIndex =
        if (isUnknownOrNeutral.indexOfFirst { false } != -1) isUnknownOrNeutral.indexOfFirst { false } else 0

    var numberOfUnstables = 0
    for (entry in isUnstable) if (entry) numberOfUnstables++

    val isCurrentlyUnstable =
        (isUnstable[0] && streaks[0] > 3) || averageStreakLength < 4 || numberOfUnstables > (0.4 * streaks.size)

    var stateAnalysis = State.UNKNOWN
    if (entries.size < 5) {
        stateAnalysis = State.UNKNOWN
    } else if (isCurrentlyUnstable) {
        stateAnalysis = State.UNSTABLE
    } else if (isUnknownOrNeutral[0] && streaks[0] > (0.6 * averageStreakLength)) {
        stateAnalysis = statesInStreaks[0]
    } else {
        for (entry in statesInStreaks) {
            if (entry != State.NEUTRAL && entry != State.UNKNOWN && entry != State.UNSTABLE) {
                stateAnalysis = entry
            }
        }
    }

    val cyclePosition: StateStatus

    var currentStreakLength = 0
    for (k in 0 until currentStartingIndex + 1) currentStreakLength += streaks[k]

    if (entries.size < cycleLength) cyclePosition = StateStatus.UNKNOWN
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