package com.example.homeworktbc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.homeworktbc.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = listOf(
            ActiveOrdersFragment(),
            CompletedOrdersFragment()
        )

        val adapter = MyFragmentAdapter(
            fragments,
            supportFragmentManager,
            lifecycle
        )

        binding.pager.adapter = adapter

        //Attach the tabLayout with viewPager
        //TabLayoutMediator is responsible to attach the viewPager with the tabLayout

        TabLayoutMediator(binding.tabLayout,binding.pager){tab , position ->
            tab.text = when(position){
                0 -> "Active"
                1 -> "Completed"
                else -> "error"
            }
        //We should attach this after calling adapter(line 28)
        }.attach()

        binding.tabLayout.setTabTextColors(
            resources.getColor(R.color.tab_unselected_color), // Unselected tab text color
            resources.getColor(R.color.white)    // Selected tab text color
        )

    }
}

