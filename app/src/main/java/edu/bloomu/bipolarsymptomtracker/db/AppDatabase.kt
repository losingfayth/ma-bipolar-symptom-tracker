package edu.bloomu.bipolarsymptomtracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Implements a Room database for Entry storage
 */
@Database(entities = [Entry::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() { abstract fun entryDao(): EntryDao }