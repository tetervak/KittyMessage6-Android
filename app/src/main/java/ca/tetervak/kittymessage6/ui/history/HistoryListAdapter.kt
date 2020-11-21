package ca.tetervak.kittymessage6.ui.history

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.databinding.FragmentHistoryItemBinding
import ca.tetervak.kittymessage6.domain.Envelope

class HistoryListAdapter()
    : ListAdapter<Envelope, HistoryListAdapter.ViewHolder>(EnvelopeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position + 1, getItem(position))
    }

    class ViewHolder private constructor(
        private val binding: FragmentHistoryItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(count: Int, envelope: Envelope) {
            binding.count.text = binding.root.context.getString(R.string.count, count)
            binding.envelope = envelope
            binding.root.setOnClickListener {
                it.findNavController()
                    .navigate(HistoryFragmentDirections.actionHistoryToOutput(envelope.id))
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentHistoryItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class EnvelopeDiffCallback : DiffUtil.ItemCallback<Envelope>() {
        override fun areItemsTheSame(oldItem: Envelope, newItem: Envelope): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Envelope, newItem: Envelope): Boolean {
            return oldItem == newItem
        }
    }

}