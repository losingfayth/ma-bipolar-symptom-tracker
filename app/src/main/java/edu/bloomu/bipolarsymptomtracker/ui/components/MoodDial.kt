package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MoodDial() {
    Row {
        Column {  }
        Column {  }
        Column {  }
        Column {  }
        Column {  }
    }
}

@Composable
fun MoodDialElement(
    icon: ImageVector,
    title: String
) {
    Row { // this should be icon (icon only)
        Text(title)
    }
}