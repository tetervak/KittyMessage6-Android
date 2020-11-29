package ca.tetervak.kittymessage6.ui.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.databinding.FragmentHistoryBinding
import ca.tetervak.kittymessage6.ui.dialogs.ConfirmationDialog
import ca.tetervak.kittymessage6.ui.settings.KittySettings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    companion object{
        const val CONFIRM_CLEAR_ALL: Int = 1
        const val CONFIRM_DELETE_ITEM: Int = 2
    }

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by viewModels()
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
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        navController = findNavController()

        // make the adapter
        val adapter = EnvelopeListAdapter(
            onClick = {
                navController.navigate(HistoryFragmentDirections.actionHistoryToOutput(it.id))
            },
            onDelete = {
                if(settings.confirmDelete){
                    val action = HistoryFragmentDirections.actionHistoryToConfirmation(
                        getString(R.string.confirm_delete_message), CONFIRM_DELETE_ITEM, it.id)
                    navController.navigate(action)
                }else{
                    viewModel.delete(it)
                }
            }
        )

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(divider)
        binding.recyclerView.adapter = adapter

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        ConfirmationDialog.setResultListener(this, R.id.history_fragment) {
            when (it?.requestCode) {
                CONFIRM_CLEAR_ALL -> { viewModel.clear() }
                CONFIRM_DELETE_ITEM -> { viewModel.delete(it.id) }
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_history, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                if(settings.confirmClear){
                    val action = HistoryFragmentDirections.actionHistoryToConfirmation(
                        getString(R.string.confirm_clear_message), CONFIRM_CLEAR_ALL)
                    navController.navigate(action)
                } else {
                    viewModel.clear()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}