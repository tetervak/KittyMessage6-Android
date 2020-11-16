package ca.tetervak.kittymessage6.ui.output

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.databinding.FragmentOutputBinding
import ca.tetervak.kittymessage6.ui.dialogs.ConfirmationDialog
import ca.tetervak.kittymessage6.ui.settings.KittySettings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OutputFragment : Fragment() {

    companion object{
        const val CONFIRM_DELETE: Int = 1
    }

    private val safeArgs: OutputFragmentArgs by navArgs()
    private val viewModel: OutputViewModel by viewModels()
    private lateinit var navController: NavController

    @Inject
    lateinit var settings: KittySettings

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

        // load the data into the viewModel
        viewModel.loadData(safeArgs.envelopeId)

        // data-bind the viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.backButton.setOnClickListener { showInput() }

        navController = findNavController()

        // show input page when the data is deleted from the database
        viewModel.status.observe(viewLifecycleOwner){
            if(it == OutputViewModel.Status.DELETE_DATA){
                showInput()
            }
        }

        // make the delete confirmation dialog work
        ConfirmationDialog.setResultListener(this, R.id.output_fragment) {
            if (it?.requestCode == CONFIRM_DELETE) {
                viewModel.delete()
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_output, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_delete -> {
                if(settings.confirmDelete){
                    val action = OutputFragmentDirections.actionOutputToConfirmation(
                        getString(R.string.confirm_delete_message), CONFIRM_DELETE)
                    navController.navigate(action)
                } else {
                    viewModel.delete()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showInput(){
        val action = OutputFragmentDirections.actionGlobalToInput()
        navController.navigate(action)
    }

}