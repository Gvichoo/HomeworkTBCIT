package com.example.homeworktbc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.RecyclersRecyclerBinding
import com.example.homeworktbc.models.FieldItem


class MainAdapter(private val onTextChanged: (Int, String?) -> Unit) :
    ListAdapter<FieldItem, MainAdapter.CardViewHolder>(CardItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = RecyclersRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardViewHolder(private val binding: RecyclersRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fieldItem: FieldItem) {
            val viewAdapter = ViewAdapter { id, text -> onTextChanged(id, text) }

            binding.rvEditTexts.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = viewAdapter
            }

            viewAdapter.submitList(fieldItem.fieldItem)
        }
    }

    companion object {
        val CardItemCallback = object : DiffUtil.ItemCallback<FieldItem>() {
            override fun areItemsTheSame(oldItem: FieldItem, newItem: FieldItem): Boolean {
                return oldItem.fieldId == newItem.fieldId
            }

            override fun areContentsTheSame(oldItem: FieldItem, newItem: FieldItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}