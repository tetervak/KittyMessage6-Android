package ca.tetervak.kittymessage6.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ca.tetervak.kittymessage6.domain.Envelope
import ca.tetervak.kittymessage6.repository.EnvelopeRepository
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EnvelopeRepository = EnvelopeRepository(application)

    val history: LiveData<List<Envelope>> = repository.getAll()

    fun clear(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

}