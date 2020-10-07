package ca.tetervak.kittymessage6.ui.output

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ca.tetervak.kittymessage6.databinding.FragmentOutputBinding

class OutputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =
            FragmentOutputBinding.inflate(inflater, container, false)

        // get the view model
        val safeArgs: OutputFragmentArgs by navArgs()
        val application = requireActivity().application
        val factory = OutputViewModelFactory(safeArgs.envelopeKey, application)
        val viewModel: OutputViewModel by viewModels { factory }

        viewModel.receive().observe(viewLifecycleOwner){
            binding.envelope = it
        }

        binding.backButton.setOnClickListener { showInput() }

        return binding.root
    }

    private fun showInput(){
        val action = OutputFragmentDirections.actionOutputToInput()
        findNavController().navigate(action)
    }


}