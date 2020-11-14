package ca.tetervak.kittymessage6.database

import androidx.room.TypeConverter
import ca.tetervak.kittymessage6.domain.CatMessage
import java.util.*

class Converters {

    @TypeConverter
    fun fromLongToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun fromDateToLong(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromCatMessageToInt(catMessage: CatMessage): Int{
        return catMessage.ordinal
    }

    @TypeConverter
    fun fromIntToCatMessage(code: Int): CatMessage{
        return CatMessage.values()[code]
    }
}