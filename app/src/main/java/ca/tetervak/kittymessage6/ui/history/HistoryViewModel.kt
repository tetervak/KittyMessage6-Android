package ca.tetervak.kittymessage6.ui.history

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.kittymessage6.domain.Envelope
import ca.tetervak.kittymessage6.repository.EnvelopeRepository
import kotlinx.coroutines.launch

class HistoryViewModel @ViewModelInject constructor(
    private val repository: EnvelopeRepository) : ViewModel() {

    val history: LiveData<List<Envelope>> = repository.getAll()

    fun clear(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

}