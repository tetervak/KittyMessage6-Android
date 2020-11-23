package ca.tetervak.kittymessage6.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ca.tetervak.kittymessage6.R

@BindingAdapter("delivery")
fun bindDelivery(textView: TextView, isUrgent: Boolean?) {
    if (isUrgent is Boolean) {
        val context = textView.context
        textView.text =
            if (isUrgent) {
                context.getString(R.string.urgent)
            } else {
                context.getString(R.string.not_urgent)
            }
    }
}
