package ca.tetervak.kittymessage6.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.tetervak.kittymessage6.domain.CatMessage
import java.util.*

@Entity(tableName = "envelopes")
data class Envelope(

    @ColumnInfo(name = "is_urgent")
    val isUrgent: Boolean,

    @ColumnInfo(name = "cat_message")
    val catMessage: CatMessage,

    @ColumnInfo(name = "date")
    val date: Date = Date(),

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
)