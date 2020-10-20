package ca.tetervak.kittymessage6.util

import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

private val formatterDate =
    DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy")

private val formatterTime = DateTimeFormatter.ofPattern("h:mm:s a")

private val formatterDateTime =
    DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy - h:mm a")

fun formatDate(date: Date?): String? {
    return date?.toInstant()
        ?.atZone(ZoneId.systemDefault())
        ?.toLocalDate()
        ?.format(formatterDate)
}

fun formatTime(date: Date?): String? {
    return date?.toInstant()
        ?.atZone(ZoneId.systemDefault())
        ?.toLocalTime()
        ?.format(formatterTime)
}

fun formatDateTime(date: Date?): String? {
    return date?.toInstant()
        ?.atZone(ZoneId.systemDefault())
        ?.toLocalDateTime()
        ?.format(formatterDateTime)
}