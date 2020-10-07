package ca.tetervak.kittymessage6.ui.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.viewModels
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.ui.history.dummy.DummyContent
import ca.tetervak.kittymessage6.ui.input.InputViewModel

/**
 * A fragment representing a list of Items.
 */
class HistoryFragment : Fragment() {

    private lateinit var adapter: HistoryRecyclerViewAdapter

    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        // Set the adapter
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        adapter = HistoryRecyclerViewAdapter(view.context)
        recyclerView.adapter = adapter

        viewModel.history.observe(viewLifecycleOwner){ adapter.history = it}

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_history, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                viewModel.clear()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}