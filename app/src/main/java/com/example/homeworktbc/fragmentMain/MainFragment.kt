package com.example.homeworktbc.fragmentMain

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.baseFragment.BaseFragment
import com.example.homeworktbc.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: ItemAdapter

    override fun start() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val chatItems = mainViewModel.parseJson() ?: emptyList()

        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        adapter = ItemAdapter()
        binding.recyclerview.adapter = adapter

        adapter.submitList(chatItems)

        binding.btnFilter.setOnClickListener {
            val query = binding.etFriends.text.toString()

            val filteredList = chatItems.filter {
                it.owner.contains(query, ignoreCase = true)
            }

            adapter.submitList(filteredList)
        }
    }
}