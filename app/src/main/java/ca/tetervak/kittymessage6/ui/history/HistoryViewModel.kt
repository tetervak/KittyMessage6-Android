package ca.tetervak.kittymessage6.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ca.tetervak.kittymessage6.database.Envelope
import ca.tetervak.kittymessage6.database.EnvelopeDao
import ca.tetervak.kittymessage6.database.EnvelopeDatabase
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val envelopeDao: EnvelopeDao =
        EnvelopeDatabase.getInstance(application).envelopeDao

    val history: LiveData<List<Envelope>> = envelopeDao.getAll()

    fun clear(){
        viewModelScope.launch {
            envelopeDao.deleteAll()
        }
    }

}