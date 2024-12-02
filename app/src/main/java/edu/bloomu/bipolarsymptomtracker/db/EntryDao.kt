package edu.bloomu.bipolarsymptomtracker.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // this *should* work to update as well
    suspend fun insert(entry: Entry)

    @Query("SELECT * FROM entries WHERE date = :date")
    suspend fun getEntry(date: String): Entry?

    @Query("SELECT * FROM entries")
    suspend fun getAll(): List<Entry>

    @Delete
    fun delete(entry: Entry)
}