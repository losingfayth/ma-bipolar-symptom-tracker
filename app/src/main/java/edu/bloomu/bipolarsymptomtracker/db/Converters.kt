package edu.bloomu.bipolarsymptomtracker.db

import androidx.room.TypeConverter
import edu.bloomu.bipolarsymptomtracker.model.Symptoms
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.bloomu.bipolarsymptomtracker.model.Mood
import edu.bloomu.bipolarsymptomtracker.model.Symptom

class Converters {
    @TypeConverter
    fun fromSymptomsObject(value: Symptoms?): String? {
        return value?.li.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toSymptomsObject(value: String?): Symptoms {
        val listType = object : TypeToken<Array<Symptom>>() {}.type
        val li: Array<Symptom>? = value?.let { Gson().fromJson(it, listType) }
        return Symptoms(li)
    }

    @TypeConverter
    fun fromMoodObject(value: Mood?): String? {
        return value?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toMoodObject(value: String?): Mood? {
        val type = object : TypeToken<Mood>() {}.type
        return value?.let { Gson().fromJson(it, type) }
    }
}