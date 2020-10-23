package ca.tetervak.kittymessage6.ui.output

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OutputViewModelFactory(
    private val envelopeId: Long,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OutputViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OutputViewModel(envelopeId, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
