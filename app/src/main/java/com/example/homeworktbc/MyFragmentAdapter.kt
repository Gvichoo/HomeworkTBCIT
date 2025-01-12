package com.example.homeworktbc

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyFragmentAdapter(
    private val fragments : List<Fragment>,
    fragmentManager : FragmentManager,
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