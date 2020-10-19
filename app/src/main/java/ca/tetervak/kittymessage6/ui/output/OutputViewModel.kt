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
    envelopeKey: Long, application: Application
) : AndroidViewModel(application) {

    enum class Status { SAVED_DATA, DELETED_DATA }
    private val _status = MutableLiveData(Status.SAVED_DATA)
    val status: LiveData<Status> = _status

    private val envelopeDao: EnvelopeDao =
        EnvelopeDatabase.getInstance(application).envelopeDao

    val envelopeData: LiveData<Envelope> = envelopeDao.get(envelopeKey)

    fun delete(){
        envelopeData.value?.let{
            viewModelScope.launch {
                envelopeDao.delete(it)
                _status.value = Status.DELETED_DATA
            }
        }
    }

}