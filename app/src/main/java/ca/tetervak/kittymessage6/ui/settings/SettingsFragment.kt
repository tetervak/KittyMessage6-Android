package ca.tetervak.kittymessage6.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import ca.tetervak.kittymessage6.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}