package edu.bloomu.bipolarsymptomtracker.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EntryViewModel(private val repository: EntryRepository) : ViewModel() {
    private val _entries = MutableStateFlow<List<Entry>>(emptyList())
    val entries: StateFlow<List<Entry>> = _entries

    fun insertEntry(entry: Entry) {
        viewModelScope.launch {
            repository.insertEntry(entry)
            loadAllEntries() // Refresh the list
        }
    }

    fun loadAllEntries() {
        viewModelScope.launch {
            _entries.value = repository.getAllEntries()
        }
    }

    fun deleteEntry(entry: Entry) {
        viewModelScope.launch {
            repository.deleteEntry(entry)
            loadAllEntries() // Refresh the list
        }
    }
}
