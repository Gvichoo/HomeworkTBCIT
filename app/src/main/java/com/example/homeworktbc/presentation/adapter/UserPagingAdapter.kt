package com.example.homeworktbc.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.RecyclerItemBinding
import com.example.homeworktbc.data.entity.User
import com.example.homeworktbc.presentation.extension.loadImagesGlide

class UserPagingAdapter : PagingDataAdapter<User, UserPagingAdapter.MyViewHolder>(UserDiffUtil) {

    object UserDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.onBind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    inner class MyViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: User) {
            binding.tvFirstName.text = item.firstName
            binding.tvEmail.text = item.email
            binding.tvLastName.text = item.lastName

            binding.ivAvatar.loadImagesGlide(item.avatar, 20)

        }
    }
}
