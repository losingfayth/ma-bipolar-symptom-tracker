package edu.bloomu.bipolarsymptomtracker.model

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(
    dateString: String
): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEEE, MMMM d yyyy", Locale.getDefault())
    val parsedDate = inputFormat.parse(dateString)
    return parsedDate?.let { outputFormat.format(it) }
}

fun formatTime(
    dateString: String
): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
    val parsedTime = inputFormat.parse(dateString)
    return parsedTime?.let { outputFormat.format(it) }
}