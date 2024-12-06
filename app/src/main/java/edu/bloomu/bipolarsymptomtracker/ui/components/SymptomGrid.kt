package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.model.Symptoms

/**
 * A list (grid) of Symptom cards for use on Edit Entry screen
 *
 * @param symptoms The Symptoms (list of Symptom objects) to display
 * @param modifier Formatting
 */
@Composable
fun SymptomList(
    symptoms: Symptoms,
    modifier: Modifier = Modifier
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val gridHeight = screenHeight * 0.8

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(gridHeight.dp)
            .padding(12.dp)
    ) {
        items(count = symptoms.li.size) { i ->
            val symptom = symptoms.li[i]

            SymptomCard(
                symptom = symptom,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}