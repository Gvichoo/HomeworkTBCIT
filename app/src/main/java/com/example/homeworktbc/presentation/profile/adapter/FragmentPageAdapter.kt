package com.example.homeworktbc.presentation.profile.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.homeworktbc.presentation.view_pager_fragments.attended_events.AttendedEventsFragment
import com.example.homeworktbc.presentation.view_pager_fragments.my_events.MyEventsFragment

class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) :FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position==0)
            AttendedEventsFragment()
        else
            MyEventsFragment()
    }
}