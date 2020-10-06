package ca.tetervak.kittymessage6.ui.output

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.tetervak.kittymessage6.R

class OutputFragment : Fragment() {

    companion object {
        fun newInstance() = OutputFragment()
    }

    private lateinit var viewModel: OutputViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.output_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OutputViewModel::class.java)
        // TODO: Use the ViewModel
    }

}