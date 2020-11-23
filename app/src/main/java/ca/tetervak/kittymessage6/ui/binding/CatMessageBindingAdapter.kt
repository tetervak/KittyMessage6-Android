package ca.tetervak.kittymessage6.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.domain.CatMessage

@BindingAdapter("message")
fun bindCatMessage(textView: TextView, catMessage: CatMessage?) {
    if (catMessage is CatMessage) {
        val catMessages = textView.resources.getStringArray(R.array.cat_messages)
        textView.text = catMessages[catMessage.ordinal]
    }
}
