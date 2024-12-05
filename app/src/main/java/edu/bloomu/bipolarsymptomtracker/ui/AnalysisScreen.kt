package edu.bloomu.bipolarsymptomtracker.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.model.MonthlyAnalysis
import edu.bloomu.bipolarsymptomtracker.model.State
import edu.bloomu.bipolarsymptomtracker.model.StateStatus
import edu.bloomu.bipolarsymptomtracker.ui.components.NewEntryFab
import edu.bloomu.bipolarsymptomtracker.ui.components.StateAnalysisIcon
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_depressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_manic
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_none
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_unstable

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

    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences(Strings.SharedPrefKeys.SharedPreferences, Context.MODE_PRIVATE)
    }

    val usersName by remember {
        mutableStateOf(sharedPreferences.getString(Strings.SharedPrefKeys.UserName, ""))
    }
    var cycleLength by remember {
        mutableIntStateOf(sharedPreferences.getInt(Strings.SharedPrefKeys.CycleLength, 0))
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

    val analysisColor: Color
    val analysisTitle: String
    val analysisDesc: String
    val analysisBody: String

    val configuration = LocalConfiguration.current
    val iconSize = (configuration.screenWidthDp / 5) * 2

    when (currentAnalysis.stateAnalysis) {
        State.MANIC -> {
            analysisColor = md_theme_light_state_manic
            analysisTitle = Strings.StateText.Manic.Title
            analysisDesc = Strings.StateText.Manic.Desc
            analysisBody = Strings.StateText.Manic.Body
        }
        State.HYPO_MANIC -> {
            analysisColor = md_theme_light_state_manic
            analysisTitle = Strings.StateText.HypoManic.Title
            analysisDesc = Strings.StateText.HypoManic.Desc
            analysisBody = Strings.StateText.HypoManic.Body
        }
        State.DEPRESSIVE -> {
            analysisColor = md_theme_light_state_depressed
            analysisTitle = Strings.StateText.Depressed.Title
            analysisDesc = Strings.StateText.Depressed.Desc
            analysisBody = Strings.StateText.Depressed.Body
        }
        State.HYPO_DEPRESSIVE -> {
            analysisColor = md_theme_light_state_depressed
            analysisTitle = Strings.StateText.HypoDepressed.Title
            analysisDesc = Strings.StateText.HypoDepressed.Desc
            analysisBody = Strings.StateText.HypoDepressed.Body
        }
        State.NEUTRAL -> {
            analysisColor = md_theme_light_state_none
            analysisTitle = Strings.StateText.Neutral.Title
            analysisDesc = Strings.StateText.Neutral.Desc
            analysisBody = Strings.StateText.Neutral.Body
        }
        State.UNKNOWN -> {
            analysisColor = md_theme_light_state_none
            analysisTitle = Strings.StateText.Unknown.Title
            analysisDesc = Strings.StateText.Unknown.Desc
            analysisBody = Strings.StateText.Unknown.Body
        }
        State.UNSTABLE -> {
            analysisColor = md_theme_light_state_unstable
            analysisTitle = Strings.StateText.Unstable.Title
            analysisDesc = Strings.StateText.Unstable.Desc
            analysisBody = Strings.StateText.Unstable.Body
        }
    }

    val rowModifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .padding(
            horizontal = 36.dp,
            vertical = 8.dp
        )

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(8.dp) // Additional padding for the content
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier
                .padding(
                    horizontal = 32.dp,
                    vertical = 0.dp
                )
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.displayMedium.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                    ) { append(Strings.AnalysisScreenText.Greeting)}
                    withStyle(
                        style = MaterialTheme.typography.displayLarge.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.tertiary,
                            )
                    ) { append("  " + usersName + "  ")}
                    withStyle(
                        style = MaterialTheme.typography.displayMedium.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                    ) { append(" !")}
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier
        ) {
            StateAnalysisIcon(
                state = currentAnalysis.stateAnalysis,
                modifier = Modifier
                    .size(iconSize.dp)
                    .padding(0.dp)
            )
        }
        Row(
            modifier = rowModifier
                .padding()
                .fillMaxWidth()
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.headlineLarge.toSpanStyle().copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) { append(Strings.AnalysisScreenText.YourState) }
                    withStyle(
                        style = MaterialTheme.typography.displayMedium.toSpanStyle().copy(
                            color = analysisColor,
                            fontWeight = FontWeight.ExtraBold)
                    ) { append(" " + analysisTitle) }
                }
            )
        }
        Row(
            modifier = rowModifier
                .padding()
                .fillMaxWidth()
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.headlineSmall.toSpanStyle().copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) { append(Strings.AnalysisScreenText.CyclePositionPrefix) }
                    withStyle(
                        style = MaterialTheme.typography.headlineMedium.toSpanStyle().copy(
                            color = analysisColor,
                            fontWeight = FontWeight.ExtraBold)
                    ) { append(currentAnalysis.stateStatus.toString()) }
                    withStyle(
                        style = MaterialTheme.typography.headlineSmall.toSpanStyle().copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) { append(Strings.AnalysisScreenText.CyclePositionSuffix) }
                }
            )
        }
        Row(
            modifier = rowModifier
                .padding()
                .fillMaxWidth()
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.headlineSmall.toSpanStyle().copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) { append(Strings.AnalysisScreenText.CycleLengthPrefix) }
                    withStyle(
                        style = MaterialTheme.typography.headlineMedium.toSpanStyle().copy(
                            color = analysisColor,
                            fontWeight = FontWeight.ExtraBold)
                    ) { append(" " + currentAnalysis.streakLength + " ") }
                    withStyle(
                        style = MaterialTheme.typography.headlineSmall.toSpanStyle().copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) { append(if (currentAnalysis.streakLength != 1) Strings.AnalysisScreenText.CycleLengthSuffix else " day") }
                }
            )
        }
        Row(
            modifier = rowModifier
                .padding(horizontal = 36.dp, vertical = 16.dp)
        ) {
            Text(
                text = analysisDesc,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Row(
            modifier = rowModifier
                .padding(horizontal = 36.dp)
        ) {
            Text(
                text = analysisBody,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun analyze(
    context: Context,
    entries: List<State>
) : MonthlyAnalysis {
    if (entries.isEmpty()) return MonthlyAnalysis()
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

    return MonthlyAnalysis(
        streakLength = currentStreakLength,
        stateAnalysis = stateAnalysis,
        stateStatus = cyclePosition
    )
}