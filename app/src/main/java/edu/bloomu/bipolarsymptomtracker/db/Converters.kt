package edu.bloomu.bipolarsymptomtracker.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.bloomu.bipolarsymptomtracker.model.MoodGroup
import edu.bloomu.bipolarsymptomtracker.model.Symptom
import edu.bloomu.bipolarsymptomtracker.model.Symptoms

/**
 * Converters serialize custom objects to be stored in the database as JSON
 * then convert them back when retrieved
 */
class Converters {

    /**
     * @return A JSON object from the given Symptoms object
     */
    @TypeConverter fun fromSymptomsObject(value: Symptoms?): String?
        { return value?.li.let { Gson().toJson(it) } }

    /**
     * @return A Symptoms object from a given JSON object
     */
    @TypeConverter
    fun toSymptomsObject(value: String?): Symptoms? {
        val listType = object : TypeToken<Array<Symptom>>() {}.type
        val li: Array<Symptom>? = value?.let { Gson().fromJson(it, listType) }
        return li?.let { Symptoms(it) }
    }

    /**
     * @return A JSON object from the given Mood object
     */
    @TypeConverter fun fromMoodObject(value: MoodGroup?): String?
        { return value?.let { Gson().toJson(it) } }

    /**
     * @return A Mood object from the given JSON object
     */
    @TypeConverter
    fun toMoodObject(value: String?): MoodGroup? {
        val type = object : TypeToken<MoodGroup>() {}.type
        return value?.let { Gson().fromJson(it, type) }
    }
}