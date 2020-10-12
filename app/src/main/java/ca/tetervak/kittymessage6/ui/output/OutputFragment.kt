package ca.tetervak.kittymessage6.ui.output

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.databinding.FragmentOutputBinding
import ca.tetervak.kittymessage6.util.DateTimeStamp
import java.util.*

class OutputFragment : Fragment() {

    private val safeArgs: OutputFragmentArgs by navArgs()

    private val viewModel: OutputViewModel by viewModels {
        OutputViewModelFactory(safeArgs.envelopeId, requireActivity().application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =
            FragmentOutputBinding.inflate(inflater, container, false)

        // data-bind the viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.backButton.setOnClickListener { showInput() }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_output, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_delete -> {
                viewModel.delete()
                showInput()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showInput(){
        val action = OutputFragmentDirections.actionOutputToInput()
        findNavController().navigate(action)
    }


}