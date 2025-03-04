package com.example.homeworktbc.presentation.my_events

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.data.remote.model.EventDto
import com.example.homeworktbc.databinding.FragmentMyEventsBinding
import com.example.homeworktbc.domain.modele.Event
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.my_events.adapter.MyEventsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyEventsFragment : BaseFragment<FragmentMyEventsBinding>(FragmentMyEventsBinding::inflate) {

    private val myEventsViewModel: MyEventsViewModel by viewModels()
    private val eventAdapter by lazy { MyEventsAdapter() }

    override fun start() {
        observeNewEvent()
        setupRecyclerView()
        observeViewModel()
    }


    private fun setupRecyclerView() {
        binding.myEventsRecycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.myEventsRecycler.adapter = eventAdapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myEventsViewModel.viewState.collect { state ->
                    eventAdapter.submitList(state.events)
                }
            }
        }
    }


    private fun observeNewEvent() {
        setFragmentResultListener("my_added_events") { _, bundle ->
            val eventName = bundle.getString("eventName") ?: ""
            val organizer = bundle.getString("organizer") ?: ""
            val date = bundle.getString("date") ?: ""
            val info = bundle.getString("info") ?: ""
            val price = bundle.getString("price") ?: ""
            val image = bundle.getString("image") ?: ""

            val newEvent = EventDto(
                id = 0,
                name = eventName,
                image = image,
                organizer = organizer,
                date = date,
                info = info,
                price = price
            )

            myEventsViewModel.addNewEvent(newEvent)
        }
    }


}


