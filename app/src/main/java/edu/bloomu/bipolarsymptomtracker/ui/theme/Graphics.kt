package edu.bloomu.bipolarsymptomtracker.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun CalendarGraphic(

) {

    val modifier = Modifier
        .fillMaxSize()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = Icons.Outlined.Calendar,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .size(400.dp)
        )
        Canvas(
            modifier = modifier
        ) {

        }
    }
}

