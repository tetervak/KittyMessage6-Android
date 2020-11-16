package ca.tetervak.kittymessage6.ui.settings

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import ca.tetervak.kittymessage6.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KittySettings @Inject constructor(private val application: Application){

    private val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(application)

    val urgent: Boolean
        get() = preferences.getBoolean("urgent", true)

    val messageText: String
        get(){
            val mew = application.getString(R.string.cat_mew)
            return preferences.getString("message", mew) ?: mew
        }

    val confirmDelete: Boolean
        get() = preferences.getBoolean("confirm_delete", true)

    val confirmClear: Boolean
        get() = preferences.getBoolean("confirm_clear", true)
}