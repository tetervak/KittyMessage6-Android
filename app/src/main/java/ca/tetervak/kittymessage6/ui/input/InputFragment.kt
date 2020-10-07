package ca.tetervak.kittymessage6.ui.input

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.database.Envelope
import ca.tetervak.kittymessage6.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private val viewModel: InputViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInputBinding.inflate(inflater, container, false)

        binding.sendButton.setOnClickListener { send() }

        viewModel.envelopeId.observe(viewLifecycleOwner){
            if(it > 0){
                showOutput(it)
            }
        }

        return binding.root
    }

    private fun send(){
        // get urgent flag value
        val isUrgent: Boolean = binding.urgentCheckBox.isChecked
        // get the selected message text
        val textMessage = when (binding.messageGroup.checkedRadioButtonId) {
            R.id.purr_button -> getString(R.string.cat_purr)
            R.id.mew_button -> getString(R.string.cat_mew)
            R.id.hiss_button -> getString(R.string.cat_hiss)
            else -> getString(R.string.undefined)
        }
        viewModel.send(Envelope(0, isUrgent, textMessage))
    }

    override fun onStop() {
        super.onStop()
        viewModel.reset()
    }

    private fun showOutput(envelopeId: Long) {

        val action = InputFragmentDirections.actionInputToOutput(envelopeId)
        findNavController().navigate(action)
    }

}