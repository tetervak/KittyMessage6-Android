package ca.tetervak.kittymessage6.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ca.tetervak.kittymessage6.R
import ca.tetervak.kittymessage6.domain.CatMessage
import ca.tetervak.kittymessage6.util.DateTimeStamp
import java.util.*

@BindingAdapter("app:date")
fun bindDate(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = DateTimeStamp.formatDate(date)
}

@BindingAdapter("app:time")
fun bindTime(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = DateTimeStamp.formatTime(date)
}

@BindingAdapter("app:date_time")
fun bindDateTime(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = DateTimeStamp.formatDateTime(date)
}

@BindingAdapter("app:message")
fun bindCatMessage(textView: TextView, catMessage: CatMessage?) {
    if (catMessage is CatMessage) {
        val catMessages = textView.resources.getStringArray(R.array.cat_messages)
        textView.text = catMessages[catMessage.ordinal]
    }
}

@BindingAdapter("app:delivery")
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




