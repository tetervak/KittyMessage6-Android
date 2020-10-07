package ca.tetervak.kittymessage6.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
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

    @Query("DELETE FROM envelopes")
    suspend fun deleteAll()

}