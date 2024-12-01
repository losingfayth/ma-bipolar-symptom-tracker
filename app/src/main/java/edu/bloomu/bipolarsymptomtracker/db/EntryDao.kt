package edu.bloomu.bipolarsymptomtracker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: Entry)

    @Query("SELECT * FROM entries WHERE date = :date")
    suspend fun getEntry(date: String): Entry?
}