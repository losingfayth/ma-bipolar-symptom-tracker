package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings

/**
 * Allows the user to set/update their username
 *
 * @param currName The name currently stored
 * @param onValueChange Allows the parent to save the updated name
 */
@Composable
fun UsernameField(
    currName: String = "",
    onValueChange: (String) -> Unit = {}
) {
    var name by remember{ mutableStateOf("") }

    TextField(
        value = currName,
        onValueChange = { newValue ->
            name = newValue
            onValueChange(name)
        },
        textStyle = MaterialTheme.typography.titleMedium,
        singleLine = true,
        placeholder = {
            Text(
                text = Strings.Components.UsernameField.placeholder,
                style = MaterialTheme.typography.titleMedium
            )
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}

/**
 * Formats strings to make a certain portion of the string appear more prominently
 *
 * @param prefix The section of string before the highlighted portion
 * @param prefixStyle The text formatting for the prefix
 * @param highlight The section of string to display prominently
 * @param highlightStyle The text formatting for the highlighted portion
 * @param suffix The section of string after the highlighted portion
 * @param suffixStyle The text formatting for the suffix
 * @param baseColor The color to use for the prefix, suffix
 * @param highlightColor The color to use for the highlighted portion
 * @param modifier Formatting
 */
@Composable
fun HighlightText(
    prefix: String,
    prefixStyle: TextStyle,
    highlight: String,
    highlightStyle: TextStyle,
    suffix: String? = null,
    suffixStyle: TextStyle? = null,
    baseColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    highlightColor: Color = MaterialTheme.colorScheme.tertiary,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = prefixStyle.toSpanStyle().copy(color = baseColor)
            ) { append(prefix) }

            withStyle(
                style = highlightStyle.toSpanStyle().copy(
                    color = highlightColor,
                    fontWeight = FontWeight.ExtraBold)
            ) { append(" " + highlight + " ") }

            if (suffix != null && suffixStyle != null) {
                withStyle(
                    style = suffixStyle.toSpanStyle().copy(
                        color = baseColor
                    )
                ) { append(suffix) }
            }
        },
        modifier = modifier
    )
}