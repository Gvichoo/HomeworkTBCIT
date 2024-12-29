package com.example.homeworktbc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val itemAdapter by lazy { ItemCategoryAdapter(::onItemClick) }
    private var itemList: List<Item> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }

        itemAdapter.submitList(itemList)
    }

    private fun onItemClick(item: Item) {
        findNavController().navigate(R.id.statusFragment)
    }

    fun updateItemStatus(updatedItem: Item) {
        itemList = itemList.map { if (it.id == updatedItem.id) updatedItem else it }
        itemAdapter.submitList(itemList)
    }
}
