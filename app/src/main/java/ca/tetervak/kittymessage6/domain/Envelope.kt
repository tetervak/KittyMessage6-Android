package ca.tetervak.kittymessage6.domain

import java.util.*

interface Envelope {
    val isUrgent: Boolean
    val catMessage: CatMessage
    val date: Date
    val id: Long
}