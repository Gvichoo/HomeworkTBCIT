package com.example.homeworktbc.presentation.view_pager_fragments.attended_events

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.databinding.FragmentAttendedEventsBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.view_pager_fragments.attended_events.adapter.AttendedEventAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AttendedEventsFragment : BaseFragment<FragmentAttendedEventsBinding>(FragmentAttendedEventsBinding::inflate) {

    private val attendedEventViewModel : AttendedEventsViewModel by viewModels()

    private val attendEventAdapted by lazy {
        AttendedEventAdapter(onClick = {attendedEventViewModel.deleteEvents(it)})
    }

    override fun start() {

        binding.attendedEventsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = attendEventAdapted

        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                attendedEventViewModel.uiState.collectLatest {
                    attendEventAdapted.submitList(it?.toList())
                }
            }
        }

    }

}