package edu.bloomu.bipolarsymptomtracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import edu.bloomu.bipolarsymptomtracker.model.Symptoms

@Composable
fun SymptomList(
    symptoms: Symptoms,
    borderColor: Color,

) {
    LazyColumn {
        items(count = symptoms.li.size) { i ->
            val item = symptoms.li[i]

        }
    }
}