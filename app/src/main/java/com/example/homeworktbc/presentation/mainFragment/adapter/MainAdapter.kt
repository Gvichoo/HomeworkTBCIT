package com.example.homeworktbc.presentation.mainFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.ItemCategoryBinding
import com.example.homeworktbc.presentation.model.CategoryPresentation

class MainAdapter : ListAdapter<CategoryPresentation, MainAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryPresentation) {
            binding.tvHeavyExcavator.text = category.name
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<CategoryPresentation>() {
        override fun areItemsTheSame(oldItem: CategoryPresentation, newItem: CategoryPresentation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryPresentation, newItem: CategoryPresentation): Boolean {
            return oldItem == newItem
        }
    }
}