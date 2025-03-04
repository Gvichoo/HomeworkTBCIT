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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var adapter: FragmentPageAdapter


    override fun start() {

        viewModel.loadEmail()

        observeEmail()
        observeEffects()
        startSettingsClickListener()
        setUpTabLayoutAndViewPager()


    }


    private fun setUpTabLayoutAndViewPager(){
        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager

        adapter = FragmentPageAdapter(this)


        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.attended_Events)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.added_events)))

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
                    binding.tvGmail.text = state.email.ifEmpty { getString(R.string.no_email_found) }
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
                if (!childFragmentManager.isStateSaved && isResumed) {
                    binding.root.post {
                        findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
                    }
                }
            }
        }
    }
}