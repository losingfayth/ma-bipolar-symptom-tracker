package edu.bloomu.bipolarsymptomtracker.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.bloomu.bipolarsymptomtracker.model.Mood
import edu.bloomu.bipolarsymptomtracker.model.Symptom

@Entity(tableName = "entries")
data class Entry(
    @PrimaryKey val date: String,
    val symptoms: Array<Symptom>,
    val moods: Array<Mood>,
    val meds: Boolean,
    val drugs: Boolean
) {
}
