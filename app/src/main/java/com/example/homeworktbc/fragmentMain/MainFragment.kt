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

        // Parse the JSON and get the data
        val chatItems = mainViewModel.parseJson() ?: emptyList()

        // Set up the RecyclerView
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        // Set up the adapter with the parsed data (empty list as fallback)
        adapter = ItemAdapter()
        binding.recyclerview.adapter = adapter

        // Submit the parsed list to the adapter
        adapter.submitList(chatItems)

        // Handle search button click to filter the list based on friend's name
        binding.btnFilter.setOnClickListener {
            val query = binding.etFriends.text.toString() // Get the text from the EditText

            // Filter the chat list based on the search query
            val filteredList = chatItems.filter {
                it.owner.contains(query, ignoreCase = true) // Match based on name (owner)
            }

            // Submit the filtered list to the adapter
            adapter.submitList(filteredList)
        }
    }
}