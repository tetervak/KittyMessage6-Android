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

    private val _envelopeId = MutableLiveData<Long>().apply{
        value = 0L
    }

    val envelopeId: LiveData<Long>
    get() = _envelopeId

    private val envelopeDao: EnvelopeDao =
        EnvelopeDatabase.getInstance(application).envelopeDao

    fun send(envelope: Envelope){
        viewModelScope.launch {
            _envelopeId.value = envelopeDao.insert(envelope)
        }
    }
}