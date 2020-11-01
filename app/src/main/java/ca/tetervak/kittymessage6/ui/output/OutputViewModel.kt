package ca.tetervak.kittymessage6.ui.output

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

class OutputViewModel(
    envelopeId: Long, application: Application
) : AndroidViewModel(application) {

    enum class Status { SAVED_DATA, DELETED_DATA }
    data class State(val status: Status, val envelopeId: Long?)

    companion object{
        val DELETED_DATA_SATE: State = State(Status.DELETED_DATA, null)
    }

    private val _state = MutableLiveData(State(Status.SAVED_DATA, envelopeId))
    val state: LiveData<State> = _state

    private val repository: EnvelopeRepository =
        EntryPointAccessors.fromApplication(application,
            EnvelopeRepositoryEntryPoint::class.java).envelopeRepository()

    val envelopeData: LiveData<Envelope> = repository.get(envelopeId)

    fun delete(){
        envelopeData.value?.let{
            viewModelScope.launch {
                repository.delete(it)
                _state.value = DELETED_DATA_SATE
            }
        }
    }

}