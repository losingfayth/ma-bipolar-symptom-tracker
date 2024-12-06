package edu.bloomu.bipolarsymptomtracker.model

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * @param dateString A date+time in standard format
 * @return a date formatted like 'Monday, January 1, 1970'
 */
fun formatDate(dateString: String): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
    val parsedDate = inputFormat.parse(dateString)
    return parsedDate?.let { outputFormat.format(it) }
}

/**
 * @param dateString A date+time in standard format
 * @return a time formatted like '1:00 PM'
 */
fun formatTime(dateString: String): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
    val parsedTime = inputFormat.parse(dateString)
    return parsedTime?.let { outputFormat.format(it) }
}