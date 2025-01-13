package com.example.homeworktbc

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyFragmentAdapter(
    private val fragments : List<Fragment>,
    //Manages fragments lifecycle
    fragmentManager : FragmentManager,
    //Provides lifecycle awareness for the adapter to optimize memory usage and ensure fragments are appropriately managed.
    lifeCycle : Lifecycle
) : FragmentStateAdapter(
    fragmentManager,
    lifeCycle
) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}