package com.example.homeworktbc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.CompletedRecyclerItemBinding


class CompletedFragmentsAdapter :
    ListAdapter<Items2, CompletedFragmentsAdapter.CompletedFragmentViewHolder>(ItemDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedFragmentViewHolder {
        val binding = CompletedRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompletedFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompletedFragmentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class CompletedFragmentViewHolder(private val binding: CompletedRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Items2) {
            binding.tvSkami.text = item.name
            binding.tvColor.text = item.color
            binding.tvQty.text = item.qtyNum.toString()
            binding.tvPrice.text = item.price

            binding.ivSkami.setImageResource(item.image)
            binding.btnLeaveReview.setOnClickListener {
                val bottomSheetFragment = OrdersReviewFragment()
                bottomSheetFragment.show(
                    (itemView.context as androidx.fragment.app.FragmentActivity).supportFragmentManager,
                    bottomSheetFragment.tag
                )
            }


            val color = item.color.lowercase()
            val circle = binding.circle

            when (color) {
                "red" -> circle.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.red))
                "brown" -> circle.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.brown))
                "blue" -> circle.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.blue))
                "green" -> circle.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.green))
                "black" -> circle.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.black))
                else -> circle.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.defaultColor))
            }
        }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<Items2>() {
        override fun areItemsTheSame(oldItem: Items2, newItem: Items2): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Items2, newItem: Items2): Boolean {
            return oldItem == newItem
        }
    }
}