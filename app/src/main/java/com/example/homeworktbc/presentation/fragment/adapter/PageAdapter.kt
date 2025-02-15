package com.example.homeworktbc.presentation.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworktbc.data.remote.PageData
import com.example.homeworktbc.databinding.PageItemBinding

class PageAdapter : ListAdapter<PageData,PageAdapter.PageAdapterViewHolder>(DiffCallBack) {


    inner class PageAdapterViewHolder(private val binding:PageItemBinding ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: PageData){
            binding.apply {
                price.text = item.price
                title.text = item.title
                location.text = item.location
                reactionCount.text = item.reactionCount.toString()
                ratingBar.rating = item.rate ?: 0f
                Glide.with(itemView)
                    .load(item.cover)
                    .into(cover)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageAdapterViewHolder {
        val binding = PageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PageAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PageAdapterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    object DiffCallBack : DiffUtil.ItemCallback<PageData>() {
        override fun areItemsTheSame(oldItem: PageData, newItem: PageData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PageData, newItem: PageData): Boolean {
            return oldItem == newItem
        }
    }

}