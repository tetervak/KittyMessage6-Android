package ca.tetervak.kittymessage6.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EnvelopeDao {

    @Insert
    suspend fun insert(envelope: EnvelopeEntity): Long

//    @Query("SELECT * FROM envelopes WHERE id=:id")
//    fun get(id: Long) : LiveData<EnvelopeEntity>

    @Query("SELECT * FROM envelopes WHERE id=:id")
    fun getFlow(id: Long): Flow<EnvelopeEntity>

//    @Query("SELECT * FROM envelopes ORDER BY date DESC")
//    fun getAll() : LiveData<List<EnvelopeEntity>>

    @Query("SELECT * FROM envelopes ORDER BY date DESC")
    fun getAllFlow() : Flow<List<EnvelopeEntity>>

    @Delete
    suspend fun delete(envelope: EnvelopeEntity)

    @Query("DELETE FROM envelopes WHERE id=:id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM envelopes")
    suspend fun deleteAll()

}