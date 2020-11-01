package ca.tetervak.kittymessage6.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ca.tetervak.kittymessage6.database.EnvelopeDao
import ca.tetervak.kittymessage6.database.EnvelopeDatabase
import ca.tetervak.kittymessage6.database.EnvelopeEntity
import ca.tetervak.kittymessage6.domain.Envelope
import ca.tetervak.kittymessage6.domain.EnvelopeDto

class EnvelopeRepository(application: Application) {

    private val envelopeDao: EnvelopeDao =
        EnvelopeDatabase.getInstance(application.applicationContext).envelopeDao

    suspend fun insert(envelope: Envelope): Long {
        return envelopeDao.insert(envelope.asEntity())
    }

    fun get(id: Long): LiveData<Envelope> {
        return Transformations.map(envelopeDao.get(id)) { it.asEnvelope() }
    }

    fun getAll() : LiveData<List<Envelope>> {
        return Transformations.map(envelopeDao.getAll()) { list -> list.map { it.asEnvelope() }}
    }

    suspend fun delete(envelope: Envelope){
        envelopeDao.delete(envelope.id)
    }

    suspend fun delete(id: Long){
        envelopeDao.delete(id)
    }

    suspend fun deleteAll(){
        envelopeDao.deleteAll()
    }
}

fun Envelope.asEntity(): EnvelopeEntity{
    return EnvelopeEntity(isUrgent, catMessage, date, id)
}

fun EnvelopeEntity.asEnvelope(): Envelope{
    return EnvelopeDto(isUrgent, catMessage, date, id)
}