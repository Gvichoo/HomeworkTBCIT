package com.example.homeworktbc.presentation.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.databinding.FragmentProfileBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.profile.effect.ProfileEffect
import com.example.homeworktbc.presentation.profile.event.ProfileEvent
import com.example.homeworktbc.presentation.profile.state.ProfileState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.navigation.navOptions

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun start() {
        observeState()
        observeEffects()
        startLogOutClickListener()
    }

    private fun startLogOutClickListener(){
        binding.btnLogOut.setOnClickListener {
            viewModel.obtainEvent(ProfileEvent.Logout)
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { state ->
                    handleState(state)
                }
            }
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

    private fun handleState(state: ProfileState) {
        binding.btnLogOut.isEnabled = !state.isLoggingOut
    }

    private fun handleEffect(effect: ProfileEffect) {
        when (effect) {
            ProfileEffect.NavigateToLogin -> {
                findNavController().navigate(R.id.action_profileFragment_to_logInFragment,
                    null,
                    navOptions {
                        popUpTo(R.id.nav_graph) { inclusive = true }
                        launchSingleTop = true
                    }
                )
            }

            ProfileEffect.NavigateToSetting -> TODO()
        }
    }
}