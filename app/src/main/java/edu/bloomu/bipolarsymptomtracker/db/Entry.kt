package edu.bloomu.bipolarsymptomtracker.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import edu.bloomu.bipolarsymptomtracker.model.Mood
import edu.bloomu.bipolarsymptomtracker.model.MoodLevel
import edu.bloomu.bipolarsymptomtracker.model.State
import edu.bloomu.bipolarsymptomtracker.model.Symptoms

@Entity(tableName = "entries")
data class Entry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "symptoms") val symptoms: Symptoms,
    @ColumnInfo(name = "mood") val mood: Mood,
    @ColumnInfo(name = "meds") val meds: Boolean = false,
    @ColumnInfo(name = "drugs") val drugs: Boolean = false
) {
    @get:Ignore
    val analysis: State
        get() {
            val maniaCount = symptoms.li.count { it.getIndicates() == State.MANIC } +
                    when {
                        mood.getMood(MoodLevel.VERY_HIGH) -> 2
                        mood.getMood(MoodLevel.HIGH) -> 1
                        else -> 0
                    }

            val depressiveCount = symptoms.li.count { it.getIndicates() == State.DEPRESSIVE } +
                    when {
                        mood.getMood(MoodLevel.VERY_LOW) -> 2
                        mood.getMood(MoodLevel.LOW) -> 1
                        else -> 0
                    }

            val result = maniaCount - depressiveCount
            val symptomThreshold = symptoms.li.size / 2

            return when {
                result > symptomThreshold / 2 -> State.MANIC
                result > 1 -> State.HYPO_MANIC
                result > -1 -> {
                    if (mood.getMood(MoodLevel.VERY_HIGH) && mood.getMood(MoodLevel.VERY_LOW) ||
                        maniaCount > symptomThreshold / 2) State.UNSTABLE
                    else State.NEUTRAL
                }
                result > (symptomThreshold / 2) * -1 -> State.HYPO_DEPRESSIVE
                else -> State.DEPRESSIVE
            }
        }
}
