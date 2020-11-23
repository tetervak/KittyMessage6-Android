package ca.tetervak.kittymessage6.ui.input

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import ca.tetervak.kittymessage6.domain.Envelope
import ca.tetervak.kittymessage6.repository.EnvelopeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InputViewModel @ViewModelInject constructor(
    private val repository: EnvelopeRepository) : ViewModel() {

    enum class Status { NEW_DATA, SAVED_DATA }

    var envelopeId: Long? = null

    private val _status = MutableLiveData(Status.NEW_DATA)
    val status: LiveData<Status> = _status

    fun save(envelope: Envelope){
        viewModelScope.launch(Dispatchers.IO) {
            envelopeId = repository.insert(envelope)
            _status.value = Status.SAVED_DATA
        }
    }

    fun reset(){
        _status.value = Status.NEW_DATA
    }
}