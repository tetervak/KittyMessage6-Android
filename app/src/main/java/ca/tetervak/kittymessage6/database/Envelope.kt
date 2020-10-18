package ca.tetervak.kittymessage6.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "envelopes")
data class Envelope(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "is_urgent")
    val isUrgent: Boolean,

    @ColumnInfo(name = "text_message")
    val textMessage: String,

    @ColumnInfo(name = "time_stamp")
    val timeStamp: Date
)