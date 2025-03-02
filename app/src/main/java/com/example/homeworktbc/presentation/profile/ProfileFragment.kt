package com.example.homeworktbc.presentation.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.homeworktbc.R
import com.example.homeworktbc.databinding.FragmentProfileBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.profile.adapter.FragmentPageAdapter
import com.example.homeworktbc.presentation.profile.effect.ProfileEffect
import com.example.homeworktbc.presentation.profile.event.ProfileEvent
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter : FragmentPageAdapter

    override fun start() {
        observeEffects()
        startSettingsClickListener()
        setGmail()

        tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager
        adapter = FragmentPageAdapter(parentFragmentManager, lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText("Attended Events"))
        tabLayout.addTab(tabLayout.newTab().setText("Added Events"))

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null ){
                    viewPager2.currentItem = tab.position
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    private fun setGmail() {
        parentFragmentManager.setFragmentResultListener(
            "loginResult",
            viewLifecycleOwner
        ) { _, bundle ->
            val email = bundle.getString("email")
            binding.tvGmail.text = email
        }
    }


    private fun startSettingsClickListener() {
        binding.btnSettings.setOnClickListener {
            viewModel.obtainEvent(ProfileEvent.Settings)
        }
    }


    private fun observeEffects() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effects.collect { effect ->
                    handleEffect(effect)
                }
            }
        }
    }

    private fun handleEffect(effect: ProfileEffect) {
        when (effect) {
            ProfileEffect.NavigateToSetting -> {
                val navController = findNavController()
                if (navController.currentDestination?.id != R.id.settingsFragment) {
                    navController.navigate(R.id.action_profileFragment_to_settingsFragment)
                }
            }
        }
    }
}