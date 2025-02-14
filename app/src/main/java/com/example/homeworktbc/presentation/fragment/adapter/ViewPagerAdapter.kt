package com.example.homeworktbc.presentation.fragment.adapter

import android.graphics.pdf.PdfDocument.Page
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworktbc.data.Item
import com.example.homeworktbc.databinding.PageItemBinding

class ViewPagerAdapter : ListAdapter<Item, ViewPagerAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = PageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ItemViewHolder(private val binding: PageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.title.text = item.title
            binding.price.text = item.price
            binding.location.text = item.location
            binding.reactionCount.text = item.reactionCount.toString()

            Glide.with(binding.cover.context)
                .load(item.cover)
                .into(binding.cover)

            binding.ratingBar.rating = item.rate ?: 0f
        }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}