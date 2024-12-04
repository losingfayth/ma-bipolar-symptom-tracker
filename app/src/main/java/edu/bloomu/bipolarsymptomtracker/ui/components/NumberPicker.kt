package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.ui.theme.Icons

@Composable
fun NumberPicker(
    min: Int = 0,
    max: Int = 99,
    start: Int = 3,
    onValueChange: (Int) -> Unit,
    content: @Composable () -> Unit
) {
    var value by remember { mutableIntStateOf(start.coerceIn(min, max)) }

    content()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            ArrowButton(
                painter = Icons.Outlined.DropUp, // Fixed icon
                onClick = {
                    if (value == max) value = min else value++
                    onValueChange(value) // Notify parent
                }
            )
        }
        Row {
            TextField(
                value = value.toString(),
                onValueChange = { input ->
                    // Update the value if the input is numeric and within range
                    val newValue = input.toIntOrNull()?.coerceIn(min, max)
                    if (newValue != null) {
                        value = newValue
                        onValueChange(value) // Notify parent
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.width(100.dp) // Adjust width as needed
            )
        }
        Row {
            ArrowButton(
                painter = Icons.Outlined.DropDown, // Fixed icon
                onClick = {
                    if (value == min) value = max else value--
                    onValueChange(value) // Notify parent
                }
            )
        }
    }
}
