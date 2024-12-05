package edu.bloomu.bipolarsymptomtracker.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bloomu.bipolarsymptomtracker.db.Entry
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.model.Mood
import edu.bloomu.bipolarsymptomtracker.model.Symptoms
import edu.bloomu.bipolarsymptomtracker.nav.NavigationItem
import edu.bloomu.bipolarsymptomtracker.ui.theme.Painters
import edu.bloomu.bipolarsymptomtracker.ui.theme.Units
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_button_dark
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_button_light
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_card_alt_dark
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_card_alt_light
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_light_primary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Composable
fun SwitchButton(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
    initiallySelected: Boolean = false,
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf(initiallySelected) }

    BasicCard(
        elevation = CardDefaults.cardElevation(0.dp),
        colors = if (selected) md_theme_light_card_alt_dark else md_theme_light_card_alt_light,
        border = BorderStroke(8.dp, md_theme_light_primary),
        modifier = modifier
            .clickable {
                onClick()
                selected = !selected
            }
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        horizontal = 8.dp
                    )
            ) {
                content()
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        horizontal = 8.dp
                    )
            ) {
                Switch(
                    checked = selected,
                    onCheckedChange = {
                        selected = it // Update the state when toggled
                        onClick()
                    },
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun TextSwitchButton(
    onClick: () -> Unit,
    text: String,
    initiallySelected: Boolean = false,
    modifier: Modifier = Modifier
) {
    SwitchButton(
        onClick = onClick,
        initiallySelected = initiallySelected,
        modifier = modifier,
        content = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(end = 8.dp)
            )
        }
    )
}

@Composable
fun LargeFabDefault(
    onClick: () -> Unit,
    icon: Painter,
    desc: String,
    modifier: Modifier = Modifier
) {
    LargeFloatingActionButton (
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        modifier = modifier
    ) {
        Icon(
            painter = icon,
            contentDescription = desc,
            Modifier
                .size(
                    size = Units.Scaffold.Fab.Size
                )
                .padding(
                    all = Units.Scaffold.Fab.Padding
                )
        )
    }
}

@Composable
fun SaveFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LargeFabDefault(
        onClick = { onClick() },
        icon = Painters.Outlined.Save,
        desc = "Save",
        modifier = modifier
    )
}

@Composable
fun NewEntryFab(
    viewModel: EntryViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LargeFabDefault(
        onClick = {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.insertEntry(
                    Entry(
                        date = LocalDateTime.now().toString(),
                        symptoms = Symptoms(),
                        mood = Mood()
                    )
                )

                viewModel.entries.collectLatest { entries ->
                    if (entries.isNotEmpty())
                        navController.navigate(
                            route = NavigationItem.EntryScreen.route + "/" + viewModel.getFirstEntry().id
                        )
                }
            }
        },
        icon = Painters.Outlined.New,
        desc = "",
        modifier = modifier
    )
}

@Composable
fun NavButton(
    onClick: () -> Unit,
    icon: Painter,
    text: String,
    initiallySelected: Boolean = false,
    onSelect: (() -> Unit) -> Unit = {},
    onUnselect: (() -> Unit) -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val buttonWidth = ((configuration.screenWidthDp.dp) / 5) * 2

    var buttonColors by remember { mutableStateOf(if (initiallySelected) md_theme_light_button_dark else md_theme_light_button_light) }

    val select = {
        buttonColors = md_theme_light_button_dark
    }

    val unselect = {
        buttonColors = md_theme_light_button_light
    }

    LaunchedEffect(Unit) {
        onSelect(select)
        onUnselect(unselect)
    }

    Button(
        onClick = onClick,
        modifier = Modifier
            .width(buttonWidth)
            .padding(
                horizontal = Units.Scaffold.NavButton.Button.PaddingHorizontal
            )
            .fillMaxHeight()
        ,
        colors = buttonColors
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(
                    horizontal = Units.Scaffold.NavButton.Text.PaddingHorizontal,
                    vertical = Units.Scaffold.NavButton.Text.PaddingVertical
                )
        )
    }
}