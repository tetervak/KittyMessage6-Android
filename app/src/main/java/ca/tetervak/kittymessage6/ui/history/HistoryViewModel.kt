package ca.tetervak.kittymessage6.ui.history

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import ca.tetervak.kittymessage6.domain.Envelope
import ca.tetervak.kittymessage6.repository.EnvelopeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel @ViewModelInject constructor(
    private val repository: EnvelopeRepository) : ViewModel() {

    val history: LiveData<List<Envelope>> =
        // repository.getAll()
        repository.getAllFlow().asLiveData()

    val count: LiveData<Int>
            = Transformations.map(history){ it.size }

    fun clear() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }


    fun delete(envelope: Envelope){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(envelope)
        }
    }

    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(id)
        }
    }
}