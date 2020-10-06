package ca.tetervak.kittymessage6.ui.output

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.tetervak.kittymessage6.model.Envelope

class OutputViewModel : ViewModel() {

    fun getEnvelopeById(id: Long): LiveData<Envelope> {
        return MutableLiveData<Envelope>().apply{
            value = Envelope(true, "Mew")
        }
    }
}