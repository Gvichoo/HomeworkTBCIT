package com.example.homeworktbc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.HomeViewBinding

class AddressDiffUtil : DiffUtil.ItemCallback<Home>(){
    override fun areItemsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem == newItem
    }
}

class HomeAddressAdapter :ListAdapter<Home,HomeAddressAdapter.AddressViewHolder>(AddressDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(HomeViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.onBind(getItem(position))

    }

    inner class AddressViewHolder(private val binding :HomeViewBinding ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(item: Home){
            binding.apply {
                tvAddress.text = item.address
                tvLocation.text = item.title

            }

        }

    }

}