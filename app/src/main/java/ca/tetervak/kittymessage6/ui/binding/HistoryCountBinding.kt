package ca.tetervak.kittymessage6.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ca.tetervak.kittymessage6.R

@BindingAdapter("historyTotal")
fun bindHistoryTotal(textView: TextView, count: Int?) {
    if (count is Int)
        textView.text =
            textView.resources.getQuantityString(R.plurals.history_total, count, count)
}