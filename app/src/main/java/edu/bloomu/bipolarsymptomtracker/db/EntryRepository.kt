package edu.bloomu.bipolarsymptomtracker.db

class EntryRepository(private val entryDao: EntryDao) {
    suspend fun insertEntry(entry: Entry) = entryDao.insert(entry)
    suspend fun getEntry(date: String) = entryDao.getEntry(date)
    suspend fun getAllEntries() = entryDao.getAll()
    suspend fun deleteEntry(entry: Entry) = entryDao.delete(entry)
}