package com.example.homeworktbc.presentation.event

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.databinding.FragmentEventsBinding
import com.example.homeworktbc.presentation.adapter.EventItemAdapter
import com.example.homeworktbc.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventsFragment : BaseFragment<FragmentEventsBinding>(FragmentEventsBinding::inflate) {

    private val eventViewModel: EventViewModel by viewModels()
    private val eventAdapter = EventItemAdapter()

    override fun start() {
        setupRecyclerView()
        observeEvents()
        eventViewModel.getEvents()
        Log.d("ramerame","123")
    }

    private fun setupRecyclerView() {
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = eventAdapter
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                eventViewModel.events.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            eventAdapter.submitList(resource.data)
                        }
                        is Resource.Failed -> {
                            binding.progressBar.visibility = View.GONE
                            showError(resource.message)
                        }
                    }
                }
            }
        }
    }

    private fun showError(message: String?) {
        Toast.makeText(requireContext(), message ?: "Unknown error", Toast.LENGTH_SHORT).show()
    }

}