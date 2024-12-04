package edu.bloomu.bipolarsymptomtracker.ui.components


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bloomu.bipolarsymptomtracker.R
import edu.bloomu.bipolarsymptomtracker.db.Entry
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel
import edu.bloomu.bipolarsymptomtracker.model.Mood
import edu.bloomu.bipolarsymptomtracker.model.Symptoms
import edu.bloomu.bipolarsymptomtracker.nav.NavigationItem
import edu.bloomu.bipolarsymptomtracker.ui.theme.Icons
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
        modifier = modifier
            .clickable { onClick(); selected = !selected }
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row() {
            content()
            Switch(
                checked = selected,
                onCheckedChange = { }
            )
        }
    }
}

@Composable
fun SwitchButton(
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
                color = MaterialTheme.colorScheme.primary
            )
        }
    )
}

@Composable
fun ArrowButton(
    onClick: () -> Unit,
    painter: Painter
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.primary, shape = CircleShape)
    ) {
        Icon(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
        )
    }
}


@Composable
fun SmallFabDefault(
    onClick: () -> Unit,
    icon: Int,
    desc: String,
    modifier: Modifier
) {
    SmallFloatingActionButton(
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary,
        modifier = modifier
    ) {
        Icon(painterResource(id = icon), desc)
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
            contentDescription = desc
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
        icon = Icons.Outlined.Save,
        desc = "Save",
        modifier = modifier
    )
}

@Composable
fun SaveEntryFab(
    onClick: (function: () -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    val saveEntry: (() -> Unit)? = null
    LargeFabDefault(
        onClick = { saveEntry?.invoke() },
        icon = Icons.Outlined.Save,
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
    val context = LocalContext.current
    LargeFabDefault(
        onClick = {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.insertEntry(
                    Entry(
                        date = LocalDateTime.now().toString(),
                        symptoms = Symptoms(context),
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
        icon = Icons.Outlined.New,
        desc = "",
        modifier = modifier
    )
}

@Composable
fun CancelFab(
    onClick: () -> Unit,
    modifier: Modifier
) {
    SmallFabDefault(
        onClick = { onClick() },
        icon = R.drawable.close_24px,
        desc = "Cancel",
        modifier = modifier
    )
}

@Composable
fun NavButton(
    onClick: () -> Unit,
    icon: Painter,
    text: String,
    selected: Boolean = false
) {
    val configuration = LocalConfiguration.current
    val buttonWidth = (configuration.screenWidthDp.dp) / 3

    //var selected by remember { mutableStateOf(selected) }

    val themeContainerColor = MaterialTheme.colorScheme.primaryContainer
    val themeOnContainerColor = MaterialTheme.colorScheme.onPrimaryContainer

    var containerColor by remember { mutableStateOf(if (selected) themeOnContainerColor else themeContainerColor) }
    var onContainerColor by remember { mutableStateOf(if (selected) themeContainerColor else themeOnContainerColor) }

    Button(
        onClick = {
            onClick()

            if (selected) {
                containerColor = themeOnContainerColor
                onContainerColor = themeContainerColor
            } else {
                containerColor = themeContainerColor
                onContainerColor = themeOnContainerColor
            }
        },
        modifier = Modifier
            .width(buttonWidth)
            .padding(horizontal = 16.dp, vertical = 0.dp)
            .fillMaxHeight()
        ,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = onContainerColor,
            modifier = Modifier
                .padding(
                    end = 8.dp
                )
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = onContainerColor,
            modifier = Modifier
                .padding(
                    horizontal = 8.dp
                )
        )
    }
}