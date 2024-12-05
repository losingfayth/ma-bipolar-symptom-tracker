package edu.bloomu.bipolarsymptomtracker.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bloomu.bipolarsymptomtracker.R
import edu.bloomu.bipolarsymptomtracker.nav.NavigationItem
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.Units
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_state_depressed

@Composable
fun InitialSetup(
    onFabChange: (fab: @Composable () -> Unit) -> Unit,
    onClick: () -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences(Strings.SharedPrefKeys.SharedPreferences, Context.MODE_PRIVATE)
    }
    val editor = sharedPreferences.edit()

    var usersName by remember { mutableStateOf(Strings.WelcomeText.NameEntry) }
    var cycleLength by remember { mutableFloatStateOf(15f) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Units.Row.Padding.Horizontal,
                vertical = Units.Row.Padding.Vertical
            )

        Row(
            modifier = modifier
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.displayMedium.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                    ) { append("Hello, \t")}
                    withStyle(
                        style = MaterialTheme.typography.displayMedium.toSpanStyle()
                            .copy(
                                color = md_theme_light_state_depressed,
                            )
                    ) { append("you")}
                }
            )
        }
        Row(
            modifier = modifier
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.headlineSmall.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                    ) { append(Strings.WelcomeText.Blurb)}
                    withStyle(
                        style = MaterialTheme.typography.headlineMedium.toSpanStyle()
                            .copy(
                                color = MaterialTheme.colorScheme.tertiary,
                            )
                    ) { append(stringResource(R.string.app_name))}
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            TextField(
                value = usersName,
                onValueChange = { newValue ->
                    usersName = newValue
                },
                textStyle = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = modifier
                .padding(
                    horizontal = 36.dp,
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = Strings.WelcomeText.CycleLength,
                    style = MaterialTheme.typography.titleMedium
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = cycleLength.toInt().toString() + " days",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Slider(
                        value = cycleLength,
                        onValueChange = { cycleLength = it },
                        steps = 98,
                        valueRange = 1f..62f,
                        modifier = Modifier
                            .padding(
                                horizontal = 8.dp
                            )
                            .fillMaxWidth()
                    )

                }
            }
        }
        Row(
            modifier = modifier
        ) {
            Text(
                text = Strings.WelcomeText.Disclaimer,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Row {
            Button(
                onClick = {
                    editor.putInt(Strings.SharedPrefKeys.CycleLength, cycleLength.toInt())
                    editor.putString(Strings.SharedPrefKeys.UserName,
                        if (usersName == Strings.WelcomeText.NameEntry) "User" else usersName)
                    editor.putBoolean(Strings.SharedPrefKeys.SetupCompleted, true)
                    editor.apply()
                    onClick()
                    navController.navigate(NavigationItem.Analysis.route)
                },
                colors = ButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.errorContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.errorContainer
                ),
                modifier = Modifier
                    .size(
                        width = 220.dp,
                        height = 64.dp
                    )
            ) {
                Text(
                    text = Strings.WelcomeText.ButtonText,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}