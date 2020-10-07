package ca.tetervak.kittymessage6.ui.history

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.database.Envelope

import ca.tetervak.kittymessage6.ui.history.dummy.DummyContent.DummyItem

class HistoryRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder>() {

    var history: List<Envelope>? = null
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val envelope = history!![position]
        holder.idView.text = "${position + 1}."
        val urgent =
            if(envelope.isUrgent)
                context.getString(R.string.urgent)
            else
                context.getString(R.string.not_urgent)
        holder.contentView.text = "$urgent ${envelope.textMessage}"

    }

    override fun getItemCount(): Int = history?.size ?: 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}