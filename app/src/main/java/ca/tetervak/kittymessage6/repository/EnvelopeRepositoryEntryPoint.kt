package ca.tetervak.kittymessage6.repository

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface EnvelopeRepositoryEntryPoint {
    fun envelopeRepository(): EnvelopeRepository
}