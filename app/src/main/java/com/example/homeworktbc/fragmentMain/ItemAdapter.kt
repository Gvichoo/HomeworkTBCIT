package com.example.homeworktbc.fragmentMain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworktbc.R
import com.example.homeworktbc.databinding.ItemChatBinding
import com.example.homeworktbc.models.ChatItem
import com.example.homeworktbc.models.MessageType

class ItemAdapter : ListAdapter<ChatItem, ItemAdapter.ChatItemViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<ChatItem>() {
        override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        val chatItem = getItem(position)
        holder.bind(chatItem)
    }

    inner class ChatItemViewHolder(private val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatItem: ChatItem) {
            binding.friendName.text = chatItem.owner
            binding.lastMessage.text = chatItem.lastMessage
            binding.lastActive.text = chatItem.lastActive
            binding.unreadMessages.text = chatItem.unreadMessages.toString()

            Glide.with(itemView.context)
                .load(chatItem.image ?: R.drawable.imageyellow)
                .into(binding.friendFoto)

            Glide.with(itemView.context)
                .load(chatItem.image ?: R.drawable.blackgirl)
                .into(binding.friendFotoLogo)

            val messageTypeIcon = when (chatItem.lastMessageType) {
                MessageType.TEXT -> null
                MessageType.VOICE -> R.drawable.voice
                MessageType.FILE -> R.drawable.file
            }

            messageTypeIcon?.let {
                binding.messageType.setImageResource(it)
            } ?: run {
                binding.messageType.setImageDrawable(null)
            }

            binding.messageType.visibility = if (messageTypeIcon == null) View.GONE else View.VISIBLE
        }
    }
}
