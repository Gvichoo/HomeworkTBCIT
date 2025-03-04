package com.example.homeworktbc.presentation.profile.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.homeworktbc.presentation.attended_events.AttendedEventsFragment
import com.example.homeworktbc.presentation.my_events.MyEventsFragment

class FragmentPageAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            AttendedEventsFragment()
        else
            MyEventsFragment()
    }
}
