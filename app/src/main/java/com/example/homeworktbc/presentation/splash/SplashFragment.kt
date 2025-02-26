package com.example.homeworktbc.presentation.splash

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.databinding.FragmentSplashBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.splash.effect.SplashEffect
import com.example.homeworktbc.presentation.splash.event.SplashEvent
import com.example.homeworktbc.presentation.splash.state.SplashState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: SplashViewModel by viewModels()

    override fun start() {
        binding.progressBar.visibility = View.VISIBLE
        observeState()
        observeEffects()
        viewModel.obtainEvent(SplashEvent.CheckSession)
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

    private fun handleState(state: SplashState) {
        binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
    }

    private fun handleEffect(effect: SplashEffect) {
        when (effect) {
            SplashEffect.NavigateToEvents -> {
                findNavController().navigate(R.id.action_splashFragment_to_eventsFragment)
            }
            SplashEffect.NavigateToLogin -> {
                findNavController().navigate(R.id.action_splashFragment_to_logInFragment)
            }
        }
    }
}