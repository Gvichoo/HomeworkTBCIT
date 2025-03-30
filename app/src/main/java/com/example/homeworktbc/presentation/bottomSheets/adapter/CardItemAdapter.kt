package com.example.homeworktbc.presentation.bottomSheets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.data.model.CardItem
import com.example.homeworktbc.databinding.FromAccountsItemBinding

class CardItemAdapter(
    private val onItemClicked: (CardItem) -> Unit
) : ListAdapter<CardItem, CardItemAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = FromAccountsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }


    class CategoryViewHolder(
        private val binding: FromAccountsItemBinding,
        private val onItemClicked: (CardItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CardItem) {
            binding.tvCardNumber.text = item.cardNumber
            binding.root.setOnClickListener { onItemClicked(item) }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<CardItem>() {
        override fun areItemsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem == newItem
        }
    }
}
