package ca.tetervak.kittymessage6.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Envelope::class], version = 1, exportSchema = false)
abstract class EnvelopeDatabase: RoomDatabase(){

    abstract val envelopeDao: EnvelopeDao

    companion object{

        @Volatile
        private var INSTANCE: EnvelopeDatabase? = null

        fun getInstance(context: Context): EnvelopeDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EnvelopeDatabase::class.java,
                        "envelop_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}