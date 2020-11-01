package ca.tetervak.kittymessage6.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindEnvelopeRepository(repository: EnvelopeRepositoryImpl): EnvelopeRepository
}