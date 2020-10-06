package ca.tetervak.kittymessage6.ui.output

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.model.Envelope

class OutputViewModel(
    private val envelopeKey: Long,
    application: Application
) : AndroidViewModel(application) {

    fun getEnvelope(): LiveData<Envelope> {
        return MutableLiveData<Envelope>().apply {
            val application = getApplication<Application>()
            value = Envelope(true, application.getString(R.string.cat_mew))
        }
    }
}