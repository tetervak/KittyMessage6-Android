package ca.tetervak.kittymessage6.ui.input

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ca.tetervak.kittymessage6.database.Envelope
import ca.tetervak.kittymessage6.database.EnvelopeDao
import ca.tetervak.kittymessage6.database.EnvelopeDatabase
import kotlinx.coroutines.launch

class InputViewModel(application: Application) : AndroidViewModel(application) {

    enum class Status { NEW_DATA, SAVED_DATA }

    data class State(val status: Status, val envelopeId: Long?);

    companion object {
        val INITIAL_SATE: State = State(Status.NEW_DATA, null)
    }

    private val _state = MutableLiveData(INITIAL_SATE)
    val state: LiveData<State> = _state

    private val envelopeDao: EnvelopeDao =
        EnvelopeDatabase.getInstance(application).envelopeDao

    fun save(envelope: Envelope){
        viewModelScope.launch {
            val envelopeId : Long = envelopeDao.insert(envelope)
            _state.value = State(Status.SAVED_DATA, envelopeId)
        }
    }

    fun reset(){
        _state.value = INITIAL_SATE
    }
}