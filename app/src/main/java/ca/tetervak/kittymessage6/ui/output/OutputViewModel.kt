package ca.tetervak.kittymessage6.ui.output

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ca.tetervak.kittymessage6.database.Envelope
import ca.tetervak.kittymessage6.database.EnvelopeDao
import ca.tetervak.kittymessage6.database.EnvelopeDatabase
import kotlinx.coroutines.launch

class OutputViewModel(
    envelopeKey: Long, application: Application
) : AndroidViewModel(application) {

    private val envelopeDao: EnvelopeDao =
        EnvelopeDatabase.getInstance(application).envelopeDao

    val mailbox: LiveData<Envelope> = envelopeDao.get(envelopeKey)

    fun delete(){
        mailbox.value?.let{
            viewModelScope.launch {
                envelopeDao.delete(it)
            }
        }
    }

}