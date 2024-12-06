package edu.bloomu.bipolarsymptomtracker.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import edu.bloomu.bipolarsymptomtracker.model.MoodGroup
import edu.bloomu.bipolarsymptomtracker.model.MoodLevel
import edu.bloomu.bipolarsymptomtracker.model.State
import edu.bloomu.bipolarsymptomtracker.model.Symptoms

/**
 * An object to store all the data collected from the user in the database
 * @param date The date when the entry was created
 * @param symptoms The symptoms that the user has logged
 * @param moodGroup The moods that the user has logged
 * @param meds Whether the user has forgotten their meds
 * @param drugs Whether the user has taken any illicit drugs
 */
@Entity(tableName = "entries")
data class Entry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "symptoms") var symptoms: Symptoms,
    @ColumnInfo(name = "mood") var moodGroup: MoodGroup,
    @ColumnInfo(name = "meds") var meds: Boolean = false,
    @ColumnInfo(name = "drugs") var drugs: Boolean = false
) {
    /**
     * Programmatically determines what Bipolar state the user for their last entry
     */
    @get:Ignore
    val state: State
        get() {

            var happies = 0
            var sads = 0
            var total = 0

            symptoms.li.forEach { symptom ->
               if (symptom.isChecked()) {
                   when (symptom.getState()) {
                       State.FULL_MANIA -> happies++
                       State.FULL_DEPRESSIVE -> sads++
                       else -> {}
                   }
                   total++
               }
            }

            if (total < 2) return State.UNKNOWN

            if (moodGroup.getMood(MoodLevel.VERY_LOW)) sads += 2
            if (moodGroup.getMood(MoodLevel.LOW)) sads++
            if (moodGroup.getMood(MoodLevel.VERY_HIGH)) happies += 2
            if (moodGroup.getMood(MoodLevel.HIGH)) happies++

            for (mood in moodGroup.getMoods()) if (mood) total++

            val bigSad = sads > happies
            val ratio: Float

            if (bigSad) ratio = sads.toFloat() / total
            else ratio = happies.toFloat() / total

            if (total > 6 && (ratio < 0.60)) return State.UNSTABLE
            if (ratio < 0.64) return State.NEUTRAL

            return if (ratio < 0.64) {
                if (total > 6) State.UNSTABLE
                else State.NEUTRAL
            } else {
                if (bigSad) {
                    if (sads > 4) State.FULL_DEPRESSIVE
                    else State.HYPO_DEPRESSIVE
                } else {
                    if (happies > 4) State.FULL_MANIA
                    else State.HYPO_MANIA
                }
            }
        }
}
