package ca.tetervak.kittymessage6.ui.history

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.database.Envelope
import ca.tetervak.kittymessage6.databinding.FragmentHistoryItemBinding

class HistoryRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder>() {

    var history: List<Envelope>? = null
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position + 1, history!![position])
    }

    override fun getItemCount(): Int = history?.size ?: 0

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


}