package ca.tetervak.kittymessage6.ui.output

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.databinding.FragmentOutputBinding
import ca.tetervak.kittymessage6.model.Envelope

class OutputFragment : Fragment() {

    private lateinit var binding: FragmentOutputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOutputBinding.inflate(inflater, container, false)



        return binding.root
    }

    private fun showInput(){
        val action = OutputFragmentDirections.actionOutputToInput()
        findNavController().navigate(action)
    }


}