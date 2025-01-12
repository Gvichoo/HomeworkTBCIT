package com.example.homeworktbc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.ActiveRecyclerItemBinding

class ActiveFragmentsAdapter :
    ListAdapter<Items, ActiveFragmentsAdapter.ActiveOrderViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveOrderViewHolder {
        val binding = ActiveRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActiveOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActiveOrderViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ActiveOrderViewHolder(private val binding: ActiveRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Items) {
            binding.tvSkami.text = item.name
            binding.tvColor.text = item.color
            binding.qtyNumber.text = item.qtyNum.toString()
            binding.tvPrice.text = item.price

            binding.ivSkami.setImageResource(item.image)

            val color = item.color.lowercase()
            val circle = binding.circle

            when (color) {
                "red" -> circle.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.red))
                "brown" -> circle.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.brown))
                "blue" -> circle.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.blue))
                "green" -> circle.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.green))
                "black" -> circle.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.black))
                else -> circle.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.defaultColor))
            }
        }


    }

    class ItemDiffCallback : DiffUtil.ItemCallback<Items>() {
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }
    }
}