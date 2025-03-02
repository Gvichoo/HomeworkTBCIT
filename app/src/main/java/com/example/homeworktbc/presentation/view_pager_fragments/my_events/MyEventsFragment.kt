package com.example.homeworktbc.presentation.view_pager_fragments.my_events

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.data.model.EventDto
import com.example.homeworktbc.databinding.FragmentMyEventsBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.view_pager_fragments.my_events.adapter.MyEventsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyEventsFragment : BaseFragment<FragmentMyEventsBinding>(FragmentMyEventsBinding::inflate) {

    private val myEventsViewModel: MyEventsViewModel by viewModels()
    private val eventAdapter by lazy {
        MyEventsAdapter()
    }

    override fun start() {
        Log.d("MyEventsFragment", "MyEventsFragment started")
        setupRecyclerView()
        observeNewEvent()
        observeState()
    }

    private fun setupRecyclerView() {
        Log.d("MyEventsFragment", "Setting up RecyclerView")
        binding.myEventsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.myEventsRecycler.adapter = eventAdapter
    }

    private fun observeNewEvent() {
        Log.d("MyEventsFragment", "Setting up FragmentResultListener")
        setFragmentResultListener("my_event") { _, bundle ->
            val newEvent = EventDto(
                id = bundle.getInt("id"),
                name = bundle.getString("name") ?: "",
                image = bundle.getString("image") ?: "",
                organizer = bundle.getString("organizer") ?: "",
                date = bundle.getString("date") ?: "",
                info = bundle.getString("info") ?: "",
                price = bundle.getString("price") ?: ""
            )

            myEventsViewModel.addNewEvent(newEvent)
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                myEventsViewModel.viewState.collect { state ->
                    Log.d("MyEventsFragment", "State updated: $state")
                    when {
                        state.isLoading -> {
                            Log.d("MyEventsFragment", "Loading events...")
                        }

                        state.events?.isNotEmpty() == true -> {
                            Log.d("MyEventsFragment", "Displaying events: ${state.events}")
                            eventAdapter.submitList(state.events)
                        }

                        state.errorMessage != null -> {
                            Log.d("MyEventsFragment", "Error: ${state.errorMessage}")
                            showMessage(state.errorMessage)
                        }
                    }
                }
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}


