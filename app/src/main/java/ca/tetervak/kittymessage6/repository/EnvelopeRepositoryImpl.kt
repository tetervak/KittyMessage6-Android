package ca.tetervak.kittymessage6.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ca.tetervak.kittymessage6.database.EnvelopeDao
import ca.tetervak.kittymessage6.database.EnvelopeDatabase
import ca.tetervak.kittymessage6.database.EnvelopeEntity
import ca.tetervak.kittymessage6.domain.Envelope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EnvelopeRepositoryImpl @Inject constructor(database: EnvelopeDatabase) : EnvelopeRepository {

    private val envelopeDao: EnvelopeDao = database.envelopeDao

    override suspend fun insert(envelope: Envelope): Long {
        return envelopeDao.insert(envelope.asEntity())
    }

//    override fun get(id: Long): LiveData<Envelope> {
//        return Transformations.map(envelopeDao.get(id)) { it?.asEnvelope() }
//    }

    override fun getFlow(id: Long): Flow<Envelope> {
        return envelopeDao.getFlow(id).map { it.asEnvelope() }
    }

//    override fun getAll() : LiveData<List<Envelope>> {
//        return Transformations.map(envelopeDao.getAll()) { list -> list.map { it.asEnvelope() }}
//    }

    override fun getAllFlow() : Flow<List<Envelope>> {
        return envelopeDao.getAllFlow().map { list -> list.map { it.asEnvelope() }}
    }

    override suspend fun delete(envelope: Envelope){
        envelopeDao.delete(envelope.id)
    }

    override suspend fun delete(id: Long){
        envelopeDao.delete(id)
    }

    override suspend fun deleteAll(){
        envelopeDao.deleteAll()
    }
}

fun Envelope.asEntity(): EnvelopeEntity{
    return EnvelopeEntity(isUrgent, catMessage, date, id)
}

fun EnvelopeEntity.asEnvelope(): Envelope{
    return Envelope(isUrgent, catMessage, date, id)
}