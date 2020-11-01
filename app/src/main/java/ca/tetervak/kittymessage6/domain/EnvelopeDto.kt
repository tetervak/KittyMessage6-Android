package ca.tetervak.kittymessage6.domain

import java.util.Date

data class EnvelopeDto(
    override val isUrgent: Boolean,
    override val catMessage: CatMessage,
    override val date: Date = Date(),
    override val id: Long = 0L
): Envelope