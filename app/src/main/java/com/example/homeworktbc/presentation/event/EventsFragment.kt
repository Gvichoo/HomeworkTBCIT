package com.example.homeworktbc.presentation.event

import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.R
import com.example.homeworktbc.data.model.Event
import com.example.homeworktbc.databinding.FragmentEventsBinding
import com.example.homeworktbc.presentation.event.adapter.EventItemAdapter
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.event.effect.EventEffect
import com.example.homeworktbc.presentation.event.event.EventEvent
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
        observeState()
        eventViewModel.getEvents()
        navToProfileClickListener()
        observeEffects()
        observeViewState()
        observeNewEvent()
        addEventsClickListener()

    }

    private fun observeNewEvent() {
        setFragmentResultListener("new_event") { _, bundle ->
            val newEvent = Event(
                id = bundle.getInt("id"),
                name = bundle.getString("name") ?: "",
                image = bundle.getString("image") ?: "",
                organizer = bundle.getString("organizer") ?: "",
                date = bundle.getString("date") ?: "",
                info = bundle.getString("info") ?: "",
                price = bundle.getString("price") ?: ""
            )

            eventViewModel.obtainEvent(EventEvent.NewEventAdded(newEvent))

            binding.recycler.post {
                binding.recycler.layoutManager?.scrollToPosition(0)
            }

        }
    }




    private fun setupRecyclerView() {
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = eventAdapter
    }


    private fun observeEffects(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                eventViewModel.effects.collect{ effect ->
                    when(effect){
                        EventEffect.NavToAddEventsFragment -> {
                            findNavController().navigate(R.id.action_eventsFragment_to_AddEventFragment)
                        }
                        EventEffect.ShowErrorMessage -> {
                            showMessage("Error while fetching data!")
                        }

                        EventEffect.ShowSuccessMessage -> {
                            showMessage("Successfully added")
                        }
                    }
                }
            }
        }
    }


    private fun observeState() {
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
                            binding.recycler.scrollToPosition(0)
                        }

                        state.errorMessage != null -> {
                            binding.progressBar.visibility = View.GONE
                            showMessage(state.errorMessage)
                        }
                    }
                }
            }
        }
    }



    private fun observeViewState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                eventViewModel.viewState.collect { state ->
                    binding.progressBar.visibility =
                        if (state.isLoading) View.VISIBLE else View.GONE
                    state.events?.let { eventAdapter.submitList(it) }
                    state.errorMessage?.let { showMessage(it) }
                }
            }
        }
    }

    private fun addEventsClickListener(){
        binding.btnAddEvent.setOnClickListener{
            eventViewModel.obtainEvent(EventEvent.AddEventClicked)
        }
    }

    private fun navToProfileClickListener() {
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_eventsFragment_to_profileFragment)
        }
    }

    private fun showMessage(message: String?) {
        Toast.makeText(requireContext(), message ?: "Unknown error", Toast.LENGTH_SHORT).show()
    }


}