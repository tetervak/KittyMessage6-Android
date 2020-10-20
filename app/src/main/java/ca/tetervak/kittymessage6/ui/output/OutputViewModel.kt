package ca.tetervak.kittymessage6.ui.output

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ca.tetervak.kittymessage6.database.Envelope
import ca.tetervak.kittymessage6.database.EnvelopeDao
import ca.tetervak.kittymessage6.database.EnvelopeDatabase
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

    private val envelopeDao: EnvelopeDao =
        EnvelopeDatabase.getInstance(application).envelopeDao

    val envelopeData: LiveData<Envelope> = envelopeDao.get(envelopeId)

    fun delete(){
        envelopeData.value?.let{
            viewModelScope.launch {
                envelopeDao.delete(it)
                _state.value = DELETED_DATA_SATE
            }
        }
    }

}