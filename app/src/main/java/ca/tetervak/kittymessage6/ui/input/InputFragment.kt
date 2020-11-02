package ca.tetervak.kittymessage6.ui.input

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.databinding.FragmentInputBinding
import ca.tetervak.kittymessage6.domain.CatMessage
import ca.tetervak.kittymessage6.domain.EnvelopeDto
import ca.tetervak.kittymessage6.ui.settings.KittySettings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private val viewModel: InputViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInputBinding.inflate(inflater, container, false)

        binding.sendButton.setOnClickListener { save() }

        // show output when the data is saved in the database
        viewModel.status.observe(viewLifecycleOwner){
            if(it == InputViewModel.Status.SAVED_DATA) showOutput(viewModel.envelopeId!!)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // set the default message from the settings
        readSettings()
    }

    private fun save(){
        // get urgent flag value
        val isUrgent: Boolean = binding.urgentCheckBox.isChecked
        // get the selected message text
        val catMessage: CatMessage = when (binding.messageGroup.checkedRadioButtonId) {
            R.id.purr_button -> CatMessage.PURR
            R.id.mew_button -> CatMessage.MEW
            R.id.hiss_button -> CatMessage.HISS
            else -> CatMessage.MEW
        }
        viewModel.save(EnvelopeDto(isUrgent, catMessage))
    }

    private fun showOutput(envelopeId: Long) {

        viewModel.reset() // prevents going more than once

        val action = InputFragmentDirections.actionInputToOutput(envelopeId)
        findNavController().navigate(action)
    }

    private fun readSettings(){

        val settings = KittySettings(requireContext())

        binding.urgentCheckBox.isChecked = settings.urgent

        binding.messageGroup.check(
            when(settings.messageText){
                getString(R.string.cat_purr) -> R.id.purr_button
                getString(R.string.cat_mew) -> R.id.mew_button
                getString(R.string.cat_hiss) -> R.id.hiss_button
                else -> R.id.mew_button
            }
        )
    }


}