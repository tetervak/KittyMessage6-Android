package ca.tetervak.kittymessage6.ui.input

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import ca.tetervak.kittymessage6.domain.Envelope
import ca.tetervak.kittymessage6.repository.EnvelopeRepository
import kotlinx.coroutines.launch

class InputViewModel @ViewModelInject constructor(
    private val repository: EnvelopeRepository) : ViewModel() {

    enum class Status { NEW_DATA, SAVED_DATA }

    data class State(val status: Status, val envelopeId: Long?);

    companion object {
        val NEW_DATA_SATE: State = State(Status.NEW_DATA, null)
    }

    private val _state = MutableLiveData(NEW_DATA_SATE)
    val state: LiveData<State> = _state

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