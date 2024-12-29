package com.example.homeworktbc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.OrderItemBinding

class ItemDiffUtil : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}

class ItemCategoryAdapter(
    private val onItemClick: (Item) -> Unit
) : ListAdapter<Item, ItemCategoryAdapter.ItemViewHolder>(ItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Item) {
            binding.apply {
                orderNumber.text = item.orderNumber
                tvStatus.text = item.status

                tvStatus.setTextColor(
                    when (item.status) {
                        "DELIVERED" -> tvStatus.Color.GREEN
                        "CANCELED" -> tvStatus.Color.RED
                        else -> tvStatus.Color.GRAY
                    }
                )
                btnDetails.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }
}