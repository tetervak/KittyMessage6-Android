package ca.tetervak.kittymessage6.ui.output

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ca.tetervak.kittymessage6.database.Envelope
import ca.tetervak.kittymessage6.database.EnvelopeDao
import ca.tetervak.kittymessage6.database.EnvelopeDatabase

class OutputViewModel(
    private val envelopeKey: Long,
    application: Application) : AndroidViewModel(application) {

    private val envelopeDao: EnvelopeDao =
        EnvelopeDatabase.getInstance(application).envelopeDao

    val mailbox: LiveData<Envelope>
    get() = envelopeDao.get(envelopeKey)

}