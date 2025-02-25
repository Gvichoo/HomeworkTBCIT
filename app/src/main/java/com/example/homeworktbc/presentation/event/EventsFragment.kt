package com.example.homeworktbc.presentation.event

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.databinding.FragmentEventsBinding
import com.example.homeworktbc.presentation.event.adapter.EventItemAdapter
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.event.effect.EventEffect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventsFragment : BaseFragment<FragmentEventsBinding>(FragmentEventsBinding::inflate) {

    private val eventViewModel: EventViewModel by viewModels()
    private val eventAdapter by lazy {
        EventItemAdapter { event ->
            val action = EventsFragmentDirections.actionEventsFragmentToDetailsFragment(
                name = event.name,
                image = event.image,
                organizer = event.organizer,
                date = event.date,
                info = event.info,
                price = event.price
            )
            findNavController().navigate(action)
        }
    }


    override fun start() {
        setupRecyclerView()
        observeEvents()
        eventViewModel.getEvents()

    }

    private fun setupRecyclerView() {
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = eventAdapter
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                eventViewModel.viewState.collect { state ->
                    when {
                        state.isLoading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        state.events != null -> {
                            binding.progressBar.visibility = View.GONE
                            eventAdapter.submitList(state.events)
                        }

                        state.errorMessage != null -> {
                            binding.progressBar.visibility = View.GONE
                            showError(state.errorMessage)
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                eventViewModel.effects.collect { effect ->
                    when (effect) {
                        EventEffect.ShowErrorMessage -> {
                            showError("Error while fetching data!")
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