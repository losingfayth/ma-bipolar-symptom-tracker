package edu.bloomu.bipolarsymptomtracker.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.bloomu.bipolarsymptomtracker.model.Symptoms

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "symptoms") val symptoms: Symptoms
) {
}