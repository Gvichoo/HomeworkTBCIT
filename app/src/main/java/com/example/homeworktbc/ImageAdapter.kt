package com.example.homeworktbc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.ImagesBinding

class ImageAdapter (var images : List<Image>):
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        return holder.bindAdapter()
    }


    inner class ImageViewHolder(private val binding: ImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindAdapter() {
            val image = images[adapterPosition]
            binding.apply {
                txtName.text = image.title
                txtPrice.text = "$${image.price}"
                ivPhoto.setImageResource(image.image)
            }
        }
    }
}