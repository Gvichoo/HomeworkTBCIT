package com.example.homeworktbc.presentation.profile

import androidx.fragment.app.FragmentManager
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
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var adapter: FragmentPageAdapter


    override fun start() {

        val currentUser = FirebaseAuth.getInstance().currentUser

        // Check if the user is logged in
        if (currentUser != null) {
            // Display the email in the TextView
            binding.tvGmail.text = currentUser.email
        } else {
            // Handle the case where the user is not logged in
            binding.tvGmail.text = "No email founddd"
        }




        viewModel.loadEmail()

        observeEmail()


        observeEffects()
        startSettingsClickListener()


        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager

        adapter = FragmentPageAdapter(this)


        tabLayout.addTab(tabLayout.newTab().setText("Attended Events"))
        tabLayout.addTab(tabLayout.newTab().setText("Added Events"))

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 1

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }


    private fun observeEmail() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { state ->
                    binding.tvGmail.text = state.email.ifEmpty { "No email found" }
                }
            }
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
                if (childFragmentManager.isStateSaved || !isResumed || childFragmentManager.isExecutingTransactions()) {
                    return
                }

                binding.root.post {
                    val navController = findNavController()
                    if (navController.currentDestination?.id == R.id.profileFragment) {
                        navController.navigate(R.id.action_profileFragment_to_settingsFragment)
                    }
                }
            }
        }
    }

    private fun FragmentManager.isExecutingTransactions(): Boolean {
        return this.isStateSaved || this.isDestroyed || this.backStackEntryCount > 0
    }

}