package edu.bloomu.bipolarsymptomtracker.ui

import android.content.Context
import android.content.SharedPreferences
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bloomu.bipolarsymptomtracker.db.Entry
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.model.Cycle
import edu.bloomu.bipolarsymptomtracker.model.CycleAnalysis
import edu.bloomu.bipolarsymptomtracker.model.CyclePosition
import edu.bloomu.bipolarsymptomtracker.model.State
import edu.bloomu.bipolarsymptomtracker.ui.components.HighlightText
import edu.bloomu.bipolarsymptomtracker.ui.components.NewEntryFab
import edu.bloomu.bipolarsymptomtracker.ui.components.StateIcon
import edu.bloomu.bipolarsymptomtracker.ui.components.SuccessDialog
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings

@Composable
fun Analysis(
    viewModel: EntryViewModel,                          // Access the entries database
    navController: NavController,                       // Navigate through app
    onFabChange: (fab: @Composable () -> Unit) -> Unit  // Dynamically update fab
) {
    // When screen is launched, initialize, then update fab with internal functions
    LaunchedEffect(Unit) {
        onFabChange {
            // Creates a new blank entry
            NewEntryFab(
                viewModel = viewModel,
                navController = navController
            )
        }
    }

    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences(Strings.SharedPrefKeys.sharedPrefs, Context.MODE_PRIVATE)
    }

    val usersName by remember {
        mutableStateOf(sharedPreferences.getString(Strings.SharedPrefKeys.userName, ""))
    }
    val cycleLength by remember {
        mutableIntStateOf(sharedPreferences.getInt(Strings.SharedPrefKeys.cycleLength, 0))
    }

    val entries by viewModel.entries.collectAsState() // Entries from database

    val currentAnalysis by remember { mutableStateOf(analyze(entries = entries.takeLast(35).reversed(),
        sharedPreferences = sharedPreferences))}

    var displayMedWarning by remember { mutableStateOf(currentAnalysis.meds) }
    var displayDrugWarning by remember { mutableStateOf(currentAnalysis.drugs) }

    val configuration = LocalConfiguration.current
    val iconSize = (configuration.screenWidthDp / 5) * 2    // Dynamic button sizing

    val displayValues = currentAnalysis.state.getDisplayValues()

    val rowModifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .padding(
            horizontal = 36.dp,
            vertical = 8.dp
        )

    SuccessDialog(
        title = Strings.Screens.Analysis.MedDialog.title,
        message = Strings.Screens.Analysis.MedDialog.body,
        isVisible = displayMedWarning,
        onConfirm = { displayMedWarning = false }
    )

    SuccessDialog(
        title = Strings.Screens.Analysis.DrugDialog.title,
        message = Strings.Screens.Analysis.DrugDialog.body,
        isVisible = displayDrugWarning,
        onConfirm = { displayDrugWarning = false }
    )

    Column( // Outer container
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(8.dp) // Additional padding for the content
            .verticalScroll(rememberScrollState())
    ) {
        Row( // Greeting
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier
                .padding(
                    horizontal = 32.dp,
                    vertical = 0.dp
                )
        ) {
            (usersName)?.let {
                HighlightText(
                    prefix = Strings.Screens.Analysis.greetingPrefix,
                    prefixStyle = MaterialTheme.typography.displayMedium,
                    highlight = it,
                    highlightStyle = MaterialTheme.typography.displayLarge,
                    suffix = "!",
                    suffixStyle = MaterialTheme.typography.displayMedium
                )
            }
        }
        Row(    // State icon
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier
        ) {
            StateIcon(
                state = currentAnalysis.state,
                modifier = Modifier
                    .size(iconSize.dp)
                    .padding(0.dp)
            )
        }
        Row(    // State title text
            modifier = rowModifier
                .fillMaxWidth()
        ) {
            HighlightText(
                prefix = Strings.Screens.Analysis.currStatePrefix,
                prefixStyle = MaterialTheme.typography.headlineLarge,
                highlight = displayValues.title,
                highlightStyle = MaterialTheme.typography.displayMedium,
                highlightColor = displayValues.color
            )
        }
        Row( // Cycle position subtitle text
            modifier = rowModifier
                .padding()
                .fillMaxWidth()
        ) {
            HighlightText(
                prefix = Strings.Screens.Analysis.cyclePosPrefix,
                prefixStyle = MaterialTheme.typography.headlineSmall,
                highlight = currentAnalysis.position.toString(),
                highlightStyle = MaterialTheme.typography.headlineMedium,
                suffix = Strings.Screens.Analysis.cyclePosSuffix,
                suffixStyle = MaterialTheme.typography.headlineSmall,
                highlightColor = displayValues.color
            )
        }
        Row(    // Streak length subtitle text
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier
                .padding()
                .fillMaxWidth()
        ) {
            HighlightText(
                prefix = Strings.Screens.Analysis.thisLengthPrefix,
                prefixStyle = MaterialTheme.typography.headlineSmall,
                highlight = " " + currentAnalysis.length.toString() + " ",
                highlightStyle = MaterialTheme.typography.headlineMedium,
                suffix = if (currentAnalysis.length == 1) " day" else " days",
                suffixStyle = MaterialTheme.typography.headlineSmall,
                highlightColor = displayValues.color
            )
            HighlightText(
                prefix = Strings.Screens.Analysis.avgLengthPrefix,
                prefixStyle = MaterialTheme.typography.titleMedium,
                highlight = cycleLength.toString(),
                highlightStyle = MaterialTheme.typography.titleLarge,
                suffix = if (cycleLength == 1) "day" else "days",
                suffixStyle = MaterialTheme.typography.titleMedium,
                highlightColor = MaterialTheme.colorScheme.primary,
                baseColor = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(start = 48.dp)
            )
        }
        Row(    // Analysis short description text
            modifier = rowModifier
                .padding(horizontal = 36.dp, vertical = 16.dp)
        ) {
            Text(
                text = displayValues.blurb,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Row(    // Analysis body text
            modifier = rowModifier
                .padding(horizontal = 36.dp)
        ) {
            Text(
                text = displayValues.body,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

fun analyze(
    entries: List<Entry>,
    sharedPreferences: SharedPreferences
) : CycleAnalysis {
    if (entries.isEmpty()) return CycleAnalysis()

    val savedCycleLength = sharedPreferences.getInt(Strings.SharedPrefKeys.cycleLength, 0)
    val editor = sharedPreferences.edit()

    val recentCycles: MutableList<Cycle> = mutableListOf(Cycle(entries[0]))

    for (entry in entries) {
        if (entry.state.isThisOrPartner(recentCycles.last().getState()))
            recentCycles.last().iterate(entry)
        else recentCycles.add(Cycle(entry = entry, length = 1))
    }

    val adjustedRecentCycles: MutableList<Cycle>  = mutableListOf(recentCycles.last())

    for (cycle in recentCycles.reversed().drop(1)) {
        if (!adjustedRecentCycles.last().groupCycle(cycle)) adjustedRecentCycles.add(cycle)
    }

    var averageRecentCycleLength = 0

    adjustedRecentCycles
        .forEach { adjustedCycle -> averageRecentCycleLength += adjustedCycle.length }
    averageRecentCycleLength /= adjustedRecentCycles.size

    val currentStateAnalysis =
        if (entries.size < 5) State.UNKNOWN
        else if (recentCycles.last().isUnstable() && recentCycles.last().length > 3) State.UNSTABLE
        else adjustedRecentCycles.last().getState()

    val adjustedCycleLength = if (entries.size < 14) savedCycleLength
        else calcCycleLength(savedCycleLength, averageRecentCycleLength)

    val cyclePosition =
        if (entries.size < savedCycleLength) CyclePosition.UNKNOWN
        else if (adjustedRecentCycles.last().length < (adjustedCycleLength * 0.33))
            CyclePosition.START
        else if (adjustedRecentCycles.last().length > (0.67 * adjustedCycleLength))
            CyclePosition.END
        else CyclePosition.MIDDLE

    editor.putInt(Strings.SharedPrefKeys.cycleLength, adjustedCycleLength)
    editor.apply()

    return CycleAnalysis(
        state = currentStateAnalysis,
        length = adjustedRecentCycles.last().length,
        position = cyclePosition,
        meds = adjustedRecentCycles.last().getMeds(),
        drugs = adjustedRecentCycles.last().getDrugs()
    )
}

fun calcCycleLength(old: Int, new: Int): Int { return (((old * 0.64) + (new * 0.36)) / 2).toInt() }

data class AnalysisDisplayValues(
    val color: Color,
    val title: String,
    val blurb: String,
    val body: String
)