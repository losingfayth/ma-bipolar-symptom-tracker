package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Formatted number slider to allow the user to adjust their stored cycle length
 *
 * @param value The value for the slider
 * @param onValueChange Allows the parent to update information when this updates
 */
@Composable
fun CycleSlider(
    value: Int,
    onValueChange: (Int) -> Unit
) {
    Slider(
        value = value.toFloat(),
        onValueChange = { onValueChange(it.toInt()) },
        steps = 98,
        valueRange = 1f..62f,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
    )
}