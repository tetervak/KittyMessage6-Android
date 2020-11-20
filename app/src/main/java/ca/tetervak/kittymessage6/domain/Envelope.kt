package ca.tetervak.kittymessage6.domain

import java.util.Date

data class Envelope(
    val isUrgent: Boolean,
    val catMessage: CatMessage,
    val date: Date = Date(),
    val id: Long = 0L
)