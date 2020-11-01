package ca.tetervak.kittymessage6.ui.input

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ca.tetervak.kittymessage6.domain.Envelope
import ca.tetervak.kittymessage6.repository.EnvelopeRepository
import ca.tetervak.kittymessage6.repository.EnvelopeRepositoryEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch

class InputViewModel(application: Application) : AndroidViewModel(application) {

    enum class Status { NEW_DATA, SAVED_DATA }

    data class State(val status: Status, val envelopeId: Long?);

    companion object {
        val NEW_DATA_SATE: State = State(Status.NEW_DATA, null)
    }

    private val _state = MutableLiveData(NEW_DATA_SATE)
    val state: LiveData<State> = _state

    private val repository: EnvelopeRepository =
        EntryPointAccessors.fromApplication(application,
            EnvelopeRepositoryEntryPoint::class.java).envelopeRepository()

    fun save(envelope: Envelope){
        viewModelScope.launch {
            val envelopeId : Long = repository.insert(envelope)
            _state.value = State(Status.SAVED_DATA, envelopeId)
        }
    }

    fun reset(){
        _state.value = NEW_DATA_SATE
    }
}