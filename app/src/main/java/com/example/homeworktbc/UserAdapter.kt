package com.example.homeworktbc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworktbc.databinding.ItemUserBinding
import com.example.homeworktbc.roomClasses.User

class UserAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = users.size

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.userName.text = "${user.firstName} ${user.lastName}"
            binding.userStatus.text = "Status: ${getStatusText(user.activation_status)}"
            Glide.with(binding.profileImage.context)
                .load(user.profileImage ?: R.drawable.placeholder)
                .into(binding.profileImage)
        }

        private fun getStatusText(status: Int): String {
            return when (status) {
                1 -> "Online"
                2 -> "Recently Active"
                3 -> "Active a few hours ago"
                else -> "Inactive"
            }
        }
    }
}