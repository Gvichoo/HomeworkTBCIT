package com.example.homeworktbc.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworktbc.data.model.PostResponse
import com.example.homeworktbc.databinding.PostItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostAdapter : ListAdapter<PostResponse, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    inner class PostViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostResponse) {
                binding.tvTitle.text = post.title
            binding.tvComments.text = "${post.comments} Comments"
            binding.tvLikes.text = "${post.likes} Likes"
            binding.tvShareContent.text = post.shareContent

            val owner = post.owner
            binding.tvFullName.text = "${owner.firstName} ${owner.lastName}"
            binding.tvPostDate.text = convertPostDate(owner.postDate)
            Glide.with(binding.profile.context).load(owner.profile ).centerCrop().circleCrop().into(binding.profile)

            if (post.images.isNullOrEmpty()) {
                binding.cardView1.visibility = View.GONE
                binding.cardview2.visibility = View.GONE
                binding.cardview3.visibility = View.GONE
            } else {
                when (post.images.size) {
                    1 -> {
                        Glide.with(binding.root).load(post.images[0]).centerCrop().into(binding.image1)
                        binding.cardview2.visibility = View.GONE
                        binding.cardview3.visibility = View.GONE
                    }
                    2 -> {
                        Glide.with(binding.root).load(post.images[0]).centerCrop().into(binding.image1)
                        Glide.with(binding.root).load(post.images[1]).centerCrop().into(binding.image2)
                        binding.cardview3.visibility = View.GONE
                    }
                    3 -> {
                        Glide.with(binding.root).load(post.images[0]).centerCrop().into(binding.image1)
                        Glide.with(binding.root).load(post.images[1]).centerCrop().into(binding.image2)
                        Glide.with(binding.root).load(post.images[2]).centerCrop().into(binding.image3)
                    }
                    else -> {
                        binding.cardView1.visibility = View.GONE
                        binding.cardview2.visibility = View.GONE
                        binding.cardview3.visibility = View.GONE
                    }
                }
            }
        }

        private fun convertPostDate(epochTime: Long): String {
            val date = Date(epochTime * 1000) // Convert seconds to milliseconds
            val sdf = SimpleDateFormat("dd MMMM 'at' h:mm a", Locale.getDefault())
            return sdf.format(date)
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<PostResponse>() {
        override fun areItemsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean {
            return oldItem == newItem
        }
    }
}