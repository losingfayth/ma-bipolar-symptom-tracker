package edu.bloomu.bipolarsymptomtracker.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import edu.bloomu.bipolarsymptomtracker.db.EntryViewModel

@Composable
fun Entries(viewModel: EntryViewModel) {
    val entries by viewModel.entries.collectAsState()

    LazyColumn {
        items(entries) { entry ->
            Row() {
                Text("Date: ${entry.date}")
                Text("Thingie: ${entry.analysis}")
            }
        }
    }
}