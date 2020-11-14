package ca.tetervak.kittymessage6.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [EnvelopeEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
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
                        "envelope_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}