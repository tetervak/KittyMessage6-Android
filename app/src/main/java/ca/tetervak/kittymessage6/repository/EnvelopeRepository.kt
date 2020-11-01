package ca.tetervak.kittymessage6.repository

import androidx.lifecycle.LiveData
import ca.tetervak.kittymessage6.domain.Envelope

interface EnvelopeRepository {
    suspend fun insert(envelope: Envelope): Long
    fun get(id: Long): LiveData<Envelope>
    fun getAll() : LiveData<List<Envelope>>
    suspend fun delete(envelope: Envelope)
    suspend fun delete(id: Long)
    suspend fun deleteAll()
}