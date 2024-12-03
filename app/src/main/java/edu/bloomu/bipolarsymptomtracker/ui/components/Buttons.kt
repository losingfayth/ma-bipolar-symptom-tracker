package edu.bloomu.bipolarsymptomtracker.ui.components


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.R

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
    icon: Int,
    desc: String,
    modifier: Modifier
) {
    LargeFloatingActionButton (
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary,
        modifier = modifier
    ) {
        Icon(painterResource(id = icon), desc)
    }
}

@Composable
fun EditFab(
    onClick: () -> Unit,
    modifier: Modifier
) {
    LargeFabDefault(
        onClick = { onClick() },
        icon = R.drawable.edit_square_24px,
        desc = "Edit",
        modifier = modifier
    )
}

@Composable
fun SaveFab(
    onClick: () -> Unit,
    modifier: Modifier
) {
    LargeFabDefault(
        onClick = { onClick() },
        icon = R.drawable.save_24px,
        desc = "Save",
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
    initiallySelected: Boolean = false
) {
    val configuration = LocalConfiguration.current
    val buttonWidth = (configuration.screenWidthDp.dp) / 3

    var selected by remember { mutableStateOf(initiallySelected) }

    val containerColor = if (selected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }

    val onContainerColor = if (selected) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSecondaryContainer
    }

    val buttonShape = if (selected) {
        RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 16.dp, // Default rounded corner size
            bottomEnd = 16.dp
        )
    }
    else { RoundedCornerShape(16.dp) }

    Button(
        onClick = {
            onClick()
            selected = !selected
        },
        modifier = Modifier
            .width(buttonWidth)
            .padding(horizontal = 16.dp, vertical = 0.dp)
            .fillMaxHeight()
        ,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = buttonShape
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = onContainerColor,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = onContainerColor
        )
    }
}