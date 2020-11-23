package ca.tetervak.kittymessage6.ui.output

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import ca.tetervak.kittymessage6.domain.Envelope
import ca.tetervak.kittymessage6.repository.EnvelopeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OutputViewModel @ViewModelInject constructor(
    private val repository: EnvelopeRepository) : ViewModel() {

    enum class Status { NO_DATA, LOAD_DATA, DELETE_DATA }

    private var _envelopeId = MutableLiveData<Long>()

    private val _status = MutableLiveData(Status.NO_DATA)
    val status: LiveData<Status> = _status

    val envelopeData: LiveData<Envelope> =
        Transformations.switchMap(_envelopeId){ repository.get(it) }

    fun loadData(envelopeId: Long){
        _envelopeId.value = envelopeId
        _status.value = Status.LOAD_DATA
    }

    fun delete(){
        envelopeData.value?.let{
            viewModelScope.launch(Dispatchers.IO) {
                repository.delete(it)
                _status.postValue(Status.DELETE_DATA)
            }
        }
    }

}

