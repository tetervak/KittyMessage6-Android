package ca.tetervak.kittymessage6.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class DateTimeStamp {

    companion object{

        private val formatterDate =
            DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy")

        private val formatterTime = DateTimeFormatter.ofPattern("h:m:s a")

        private val formatterDateTime =
            DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy - h:m a")

        // had to annotate with @JvmStatic to use them in the binding

        @JvmStatic
        fun formatDate(date: Date?): String? {
            return date?.toInstant()
                    ?.atZone(ZoneId.systemDefault())
                    ?.toLocalDate()
                    ?.format(formatterDate)
        }

        @JvmStatic
        fun formatTime(date: Date?): String? {
            return date?.toInstant()
                        ?.atZone(ZoneId.systemDefault())
                        ?.toLocalTime()
                        ?.format(formatterTime)
        }

        @JvmStatic
        fun formatDateTime(date: Date?): String? {
            return date?.toInstant()
                        ?.atZone(ZoneId.systemDefault())
                        ?.toLocalDateTime()
                        ?.format(formatterDateTime)
        }
    }
}