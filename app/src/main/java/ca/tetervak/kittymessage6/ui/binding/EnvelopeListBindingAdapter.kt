package ca.tetervak.kittymessage6.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.tetervak.kittymessage6.domain.Envelope
import ca.tetervak.kittymessage6.ui.history.EnvelopeListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, list: List<Envelope>?) {
    val adapter = recyclerView.adapter as EnvelopeListAdapter
    adapter.submitList(list)
}
