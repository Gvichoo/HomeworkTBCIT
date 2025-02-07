package com.example.homeworktbc.fragmentNew

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.RecyclerItemBinding
import com.example.homeworktbc.fragmentNew.data.User

class UserAdapter(private val users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.binding.tvFirstName.text = user.firstName
        holder.binding.tvLastName.text = user.lastName
        holder.binding.tvEmail.text = user.email
        holder.binding.ivAvatar.setImageResource(user.avatar)
    }

    override fun getItemCount(): Int = users.size

    class UserViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)
}
