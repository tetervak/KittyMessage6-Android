package ca.tetervak.kittymessage6.ui.history

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.database.Envelope
import ca.tetervak.kittymessage6.databinding.FragmentHistoryBinding
import ca.tetervak.kittymessage6.ui.dialogs.ConfirmationDialog
import ca.tetervak.kittymessage6.ui.settings.KittySettings

/**
 * A fragment representing a list of Items.
 */
class HistoryFragment : Fragment() {

    companion object{
        const val CONFIRM_CLEAR: Int = 2
    }

    private lateinit var binding: FragmentHistoryBinding

    private lateinit var adapter: HistoryRecyclerViewAdapter

    private val viewModel: HistoryViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        // make the adapter
        adapter = HistoryRecyclerViewAdapter(requireContext())

        with(binding){
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            recyclerView.addItemDecoration(divider)
            recyclerView.adapter = adapter
        }

        viewModel.history.observe(viewLifecycleOwner){ refreshHistory(it) }

        navController = findNavController()

        ConfirmationDialog.setResultListener(this, R.id.history_fragment) {
            if (it?.requestCode == CONFIRM_CLEAR && it.resultCode == Activity.RESULT_OK) {
                clear()
            }
        }

        return binding.root
    }

    private fun refreshHistory(list: List<Envelope>?) {
        adapter.history = list
        val count = list?.size ?: 0
        binding.historyTotal.text =
            resources.getQuantityString(R.plurals.history_total, count, count)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_history, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                val settings = KittySettings(requireContext())
                if(settings.confirmClear){
                    val action = HistoryFragmentDirections.actionHistoryToConfirmation(
                        getString(R.string.confirm_clear_message), CONFIRM_CLEAR)
                    navController.navigate(action)
                } else {
                    clear()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clear(){
        viewModel.clear()
    }

}