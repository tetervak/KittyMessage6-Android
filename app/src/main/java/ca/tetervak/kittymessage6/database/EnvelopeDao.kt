package ca.tetervak.kittymessage6.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EnvelopeDao {

    @Insert
    suspend fun insert(envelope: Envelope): Long

    @Query("SELECT * FROM envelopes WHERE id=:key")
    fun get(key: Long) : LiveData<Envelope>

    @Query("SELECT * FROM envelopes ORDER BY id")
    fun getAll() : LiveData<List<Envelope>>

    @Delete
    suspend fun delete(envelope: Envelope)

    @Query("DELETE FROM envelopes WHERE id=:key")
    suspend fun delete(key: Long)

    @Query("DELETE FROM envelopes")
    suspend fun deleteAll()

}