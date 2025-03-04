package com.example.homeworktbc.presentation.view_pager_fragments.attended_events

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.databinding.FragmentAttendedEventsBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.view_pager_fragments.attended_events.adapter.AttendedEventAdapter
import com.example.homeworktbc.presentation.view_pager_fragments.attended_events.effect.AttendedEventsEffect
import com.example.homeworktbc.presentation.view_pager_fragments.attended_events.event.AttendedEventsEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AttendedEventsFragment :
    BaseFragment<FragmentAttendedEventsBinding>(FragmentAttendedEventsBinding::inflate) {

    private val attendedEventViewModel: AttendedEventsViewModel by viewModels()

    private val attendEventAdapted by lazy {
        AttendedEventAdapter(onClick = {
            attendedEventViewModel.obtainEvent(
                AttendedEventsEvent.DeleteEvent(
                    it
                )
            )
        })
    }

    override fun start() {
        setUpRecycler()
        observeEffect()
        observeState()
    }

    private fun setUpRecycler() {
        binding.attendedEventsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = attendEventAdapted
        }
    }

    private fun observeEffect() {
        viewLifecycleOwner.lifecycleScope.launch {
            attendedEventViewModel.effects.collectLatest { effect ->
                when (effect) {
                    is AttendedEventsEffect.ShowMessage -> {
                            Toast.makeText(requireContext(), effect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                attendedEventViewModel.viewState.collectLatest { state ->
                    state.events?.let {
                        attendEventAdapted.submitList(it)
                    }
                }
            }
        }
    }
}