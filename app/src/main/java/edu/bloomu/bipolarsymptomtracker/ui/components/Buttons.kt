package edu.bloomu.bipolarsymptomtracker.ui.components


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bloomu.bipolarsymptomtracker.db.Entry
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.model.MoodGroup
import edu.bloomu.bipolarsymptomtracker.model.Symptoms
import edu.bloomu.bipolarsymptomtracker.nav.NavigationItem
import edu.bloomu.bipolarsymptomtracker.ui.theme.Painters
import edu.bloomu.bipolarsymptomtracker.ui.theme.Strings
import edu.bloomu.bipolarsymptomtracker.ui.theme.Units
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_button_secondary
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_button_secondary_pressed
import edu.bloomu.bipolarsymptomtracker.ui.theme.md_theme_icon_button_pressed
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.LocalDateTime

/**
 * Stylizes delete button (mainly for entry cards)
 */
@Composable
fun DeleteButton(
    onClick: () -> Unit,
    colors: IconButtonColors = md_theme_icon_button_pressed,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { onClick() },
        colors = colors,
        modifier = modifier
            .size(Units.Icons.ExtraLarge)
            .fillMaxHeight()
    ) {
        Icon(
            painter = Painters.Outlined.Trash,
            contentDescription = Strings.Components.DeleteButton.iconAlt,
            modifier = Modifier
                .size(Units.Icons.Standard)
        )
    }
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
                .size(size = Units.Scaffold.Fab.Size)
                .padding(all = Units.Scaffold.Fab.Padding)
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
    val scope = rememberCoroutineScope()

    LargeFabDefault(
        onClick = {
            scope.launch {
                val entry = Entry(
                    date = LocalDateTime.now().toString(),
                    symptoms = Symptoms(),
                    moodGroup = MoodGroup()
                )
                viewModel.insertEntry(entry)

                viewModel.entries.firstOrNull { it.isNotEmpty() }?.let {
                    navController.navigate(
                        route = NavigationItem.EditEntry.route + "/" + viewModel.getFirstEntry().id
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

    var buttonColors by remember {
        mutableStateOf(
            if (initiallySelected) md_theme_button_secondary_pressed
            else md_theme_button_secondary)
    }

    val select = { buttonColors = md_theme_button_secondary_pressed }

    val unselect = { buttonColors = md_theme_button_secondary }

    LaunchedEffect(Unit) {
        onSelect(select)
        onUnselect(unselect)
    }

    Button(
        onClick = onClick,
        modifier = Modifier
            .width(buttonWidth)
            .padding(horizontal = Units.Scaffold.NavButton.Button.PaddingHorizontal)
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