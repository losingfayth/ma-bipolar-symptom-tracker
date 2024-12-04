package edu.bloomu.bipolarsymptomtracker.ui

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.model.State
import edu.bloomu.bipolarsymptomtracker.model.StateAnalysis
import edu.bloomu.bipolarsymptomtracker.model.StateStatus
import edu.bloomu.bipolarsymptomtracker.ui.components.NewEntryFab
import edu.bloomu.bipolarsymptomtracker.ui.theme.AppText
import edu.bloomu.bipolarsymptomtracker.ui.theme.Icons
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_depression
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_mania
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_neutrality
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_unstable

@Composable
fun Analysis(
    viewModel: EntryViewModel,
    navController: NavController,
    onFabChange: (fab: @Composable () -> Unit) -> Unit
) {
    LaunchedEffect(Unit) {
        onFabChange {
            NewEntryFab(
                viewModel = viewModel,
                navController = navController
            )
        }
    }

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

    val analysisColor: Color
    val analysisPainter: Painter
    val analysisImageDesc: String
    val analysisTitle: String
    val analysisDesc: String
    val analysisHint: String?
    val analysisBody: String

    when (currentAnalysis.stateAnalysis) {
        State.MANIC -> {
            analysisColor = md_theme_light_mania
            analysisPainter = Icons.Filled.AnalysisManic
            analysisTitle = AppText.StateText.Manic.Title
            analysisDesc = AppText.StateText.Manic.Desc
            analysisImageDesc = AppText.StateText.Manic.ImageDesc
            analysisBody = AppText.StateText.Manic.Body
        }
        State.HYPO_MANIC -> {
            analysisColor = md_theme_light_mania
            analysisPainter = Icons.Filled.AnalysisHypoManic
            analysisTitle = AppText.StateText.HypoManic.Title
            analysisDesc = AppText.StateText.HypoManic.Desc
            analysisImageDesc = AppText.StateText.HypoManic.ImageDesc
            analysisBody = AppText.StateText.HypoManic.Body
        }
        State.DEPRESSIVE -> {
            analysisColor = md_theme_light_depression
            analysisPainter = Icons.Filled.AnalysisDepressive
            analysisTitle = AppText.StateText.Depressed.Title
            analysisDesc = AppText.StateText.Depressed.Desc
            analysisImageDesc = AppText.StateText.Depressed.ImageDesc
            analysisBody = AppText.StateText.Depressed.Body
        }
        State.HYPO_DEPRESSIVE -> {
            analysisColor = md_theme_light_depression
            analysisPainter = Icons.Filled.AnalysisHypoDepressive
            analysisTitle = AppText.StateText.HypoDepressed.Title
            analysisDesc = AppText.StateText.HypoDepressed.Desc
            analysisImageDesc = AppText.StateText.HypoDepressed.ImageDesc
            analysisBody = AppText.StateText.HypoDepressed.Body
        }
        State.NEUTRAL -> {
            analysisColor = md_theme_light_neutrality
            analysisPainter = Icons.Filled.AnalysisNone
            analysisTitle = AppText.StateText.Neutral.Title
            analysisDesc = AppText.StateText.Neutral.Desc
            analysisImageDesc = AppText.StateText.Neutral.ImageDesc
            analysisBody = AppText.StateText.Neutral.Body
        }
        State.UNKNOWN -> {
            analysisColor = md_theme_light_neutrality
            analysisPainter = Icons.Filled.AnalysisNone
            analysisTitle = AppText.StateText.Unknown.Title
            analysisDesc = AppText.StateText.Unknown.Desc
            analysisImageDesc = AppText.StateText.Unknown.ImageDesc
            analysisBody = AppText.StateText.Unknown.Body
        }
        State.UNSTABLE -> {
            analysisColor = md_theme_light_unstable
            analysisPainter = Icons.Filled.AnalysisUnstable
            analysisTitle = AppText.StateText.Unstable.Title
            analysisDesc = AppText.StateText.Unstable.Desc
            analysisImageDesc = AppText.StateText.Unstable.ImageDesc
            analysisBody = AppText.StateText.Unstable.Body
        }
    }

    val rowModifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()

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
            modifier = rowModifier
        ) {
            Text("Howdy, user!")
        }
        Row(
            modifier = rowModifier
                .align(Alignment.CenterHorizontally)
        ) {
            val configuration = LocalConfiguration.current
            val iconSize = (configuration.screenWidthDp.dp / 3) * 2

            Icon(
                painter = analysisPainter,
                contentDescription = analysisImageDesc,
                tint = analysisColor,
                modifier = Modifier
                    .size(iconSize)
                    .padding(36.dp)
            )
        }
        Row(

        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.displaySmall.toSpanStyle()
                    ) { append("Looks like you're currently in an ") }
                    withStyle(
                        style = MaterialTheme.typography.displayMedium.toSpanStyle().copy(
                            color = analysisColor,
                            fontWeight = FontWeight.ExtraBold)
                    ) { append(analysisTitle) }
                    withStyle(
                        style = MaterialTheme.typography.displaySmall.toSpanStyle()
                    ) { append(" state") }
                }
            )
        }
        Row(

        ) {
            Text(
                text = analysisDesc,
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Row(

        ) {
            Text(
                text = analysisBody,
                style = MaterialTheme.typography.headlineMedium
            )
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