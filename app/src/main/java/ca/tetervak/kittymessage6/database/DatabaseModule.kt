package ca.tetervak.kittymessage6.database

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    fun provideEnvelopeDatabase(application: Application): EnvelopeDatabase{
        return EnvelopeDatabase.getInstance(application)
    }
}