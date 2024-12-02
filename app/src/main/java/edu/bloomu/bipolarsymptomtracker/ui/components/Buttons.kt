package edu.bloomu.bipolarsymptomtracker.ui.components


import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
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