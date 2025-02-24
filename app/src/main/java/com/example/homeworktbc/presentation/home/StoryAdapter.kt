package com.example.homeworktbc.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworktbc.R
import com.example.homeworktbc.data.model.StoryResponse
import com.example.homeworktbc.databinding.StoryItemBinding

class StoryAdapter : ListAdapter<StoryResponse, StoryAdapter.StoryViewHolder>(StoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = StoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StoryViewHolder(private val binding: StoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: StoryResponse) {
            binding.title.text = story.title
            Glide.with(binding.cover.context)
                .load(story.cover)
                .error(R.drawable.img)
                .into(binding.cover)

        }
    }
}

class StoryDiffCallback : DiffUtil.ItemCallback<StoryResponse>() {

    override fun areItemsTheSame(oldItem: StoryResponse, newItem: StoryResponse): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: StoryResponse, newItem: StoryResponse): Boolean =
        oldItem == newItem
}
