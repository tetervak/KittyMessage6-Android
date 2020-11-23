package ca.tetervak.kittymessage6.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ca.tetervak.kittymessage6.util.formatDate
import ca.tetervak.kittymessage6.util.formatDateTime
import ca.tetervak.kittymessage6.util.formatTime
import java.util.*

@BindingAdapter("date")
fun bindDate(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = formatDate(date)
}

@BindingAdapter("time")
fun bindTime(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = formatTime(date)
}

@BindingAdapter("date_time")
fun bindDateTime(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = formatDateTime(date)
}