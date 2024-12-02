package edu.bloomu.bipolarsymptomtracker.ui.components

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import edu.bloomu.bipolarsymptomtracker.model.Symptoms

@Composable
fun SymptomList(
    symptoms: Symptoms,
    modifier: Modifier,
) {
    LazyColumn {
        items(count = symptoms.li.size) { i ->
            val symptom = symptoms.li[i]
            SymptomCard(symptom, modifier)
        }
    }
}