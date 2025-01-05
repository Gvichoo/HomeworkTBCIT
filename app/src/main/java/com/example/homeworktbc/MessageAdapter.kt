package com.example.homeworktbc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.LeftMessageBinding
import com.example.homeworktbc.databinding.RightMessageBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MessageAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(MessageDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == LEFT_MESSAGE) {
            val binding = LeftMessageBinding.inflate(inflater, parent, false)
            LeftMessageViewHolder(binding)
        } else {
            val binding = RightMessageBinding.inflate(inflater, parent, false)
            RightMessageViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).text.hashCode() % 2 == 0) {
            LEFT_MESSAGE
        } else {
            RIGHT_MESSAGE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = getItem(position)
        if (holder is LeftMessageViewHolder) {
            holder.onBind(message)
        } else if (holder is RightMessageViewHolder) {
            holder.onBind(message)
        }
    }

    fun Long.toDate(): String {
        val calendar = Calendar.getInstance().apply { timeInMillis = this@toDate }
        val currentCalendar = Calendar.getInstance()

        return if (currentCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
            currentCalendar.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
            "Today, ${SimpleDateFormat("h:mm a", Locale.getDefault()).format(calendar.time)}"
        } else {
            SimpleDateFormat("MM/dd/yyyy h:mm a", Locale.getDefault()).format(calendar.time)
        }
    }

    inner class LeftMessageViewHolder(private val binding: LeftMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(message: Message) {
            binding.apply {
                tvMessage.text = message.text
                tvDate.text = message.dateTime.toDate()
            }
        }
    }

    inner class RightMessageViewHolder(private val binding: RightMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(message: Message) {
            binding.apply {
                tvMessage2.text = message.text
                tvDate2.text = message.dateTime.toDate()
            }
        }
    }

    companion object {
        const val LEFT_MESSAGE = 1
        const val RIGHT_MESSAGE = 2
    }
}
