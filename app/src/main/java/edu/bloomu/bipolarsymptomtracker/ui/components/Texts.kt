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

@Composable
fun UsernameField(
    currName: String,
    onValueChange: (String) -> Unit = {}
) {
    var name by remember{ mutableStateOf(currName) }
    TextField(
        value = name,
        onValueChange = { newValue ->
            name = newValue
            onValueChange(name)
        },
        textStyle = MaterialTheme.typography.titleMedium,
        singleLine = true,
        placeholder = {
            Text(
                text = Strings.WelcomeText.NameEntry,
                style = MaterialTheme.typography.titleMedium
            )
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun HighlightText(
    prefix: String,
    prefixStyle: TextStyle,
    highlight: String,
    highlightStyle: TextStyle,
    suffix: String? = null,
    suffixStyle: TextStyle? = null,
    baseColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    highlightColor: Color = MaterialTheme.colorScheme.tertiary
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
        }
    )
}