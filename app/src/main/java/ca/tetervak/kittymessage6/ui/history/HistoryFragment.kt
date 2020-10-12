package ca.tetervak.kittymessage6.ui.history

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.ui.dialogs.ConfirmationDialog
import ca.tetervak.kittymessage6.ui.settings.KittySettings

/**
 * A fragment representing a list of Items.
 */
class HistoryFragment : Fragment() {

    companion object{
        const val CONFIRM_CLEAR: Int = 2
    }

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
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

        recyclerView.addItemDecoration(
            DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))

        // Set the adapter
        adapter = HistoryRecyclerViewAdapter(view.context)
        recyclerView.adapter = adapter

        viewModel.history.observe(viewLifecycleOwner){ adapter.history = it}

        navController = findNavController()

        // make the delete confirmation dialog work
        val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
        savedStateHandle?.getLiveData<ConfirmationDialog.ConfirmationResult>(ConfirmationDialog.CONFIRMATION_RESULT)
            ?.observe(viewLifecycleOwner) {
                if(it.requestCode == CONFIRM_CLEAR && it.resultCode == Activity.RESULT_OK){
                    clear()
                }
            }

        return view
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