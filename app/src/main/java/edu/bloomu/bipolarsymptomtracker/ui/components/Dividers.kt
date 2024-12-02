package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RowDivider() {
    HorizontalDivider(
        thickness = 4.dp,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .padding(2.dp, 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    )
}