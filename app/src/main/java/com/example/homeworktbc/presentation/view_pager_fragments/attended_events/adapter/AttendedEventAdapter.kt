package com.example.homeworktbc.presentation.view_pager_fragments.attended_events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.homeworktbc.databinding.EventItemBinding
import com.example.homeworktbc.domain.modele.AttendedEvent

class AttendedEventAdapter(val onClick : (Int)-> Unit ) : ListAdapter<AttendedEvent, AttendedEventAdapter.EventViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    inner class EventViewHolder(private val binding: EventItemBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bind(event: AttendedEvent) {

            binding.name.text = event.name
            binding.organizer.text = event.organizer
            binding.date.text = event.date

            Glide.with(binding.root.context)
                .load(event.image)
                .into(binding.image)

            binding.root.setOnLongClickListener{
                onClick(event.id)
                true
            }
        }
    }

    class EventDiffCallback : DiffUtil.ItemCallback<AttendedEvent>() {
        override fun areItemsTheSame(oldItem: AttendedEvent, newItem: AttendedEvent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AttendedEvent, newItem: AttendedEvent): Boolean {
            return oldItem == newItem
        }
    }
}