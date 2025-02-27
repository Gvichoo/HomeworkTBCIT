package com.example.homeworktbc.presentation.splash

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: SplashViewModel by viewModels()

    override fun start() {
        observeEffects()
        viewModel.obtainEvent(SplashEvent.CheckSession)
    }


    private fun observeEffects() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effects.collect { effect ->
                    when (effect) {
                        SplashEffect.NavigateToEvents ->
                            navToEventFragment()

                        SplashEffect.NavigateToLogin -> {
                            navToLoginFragment()
                        }
                    }
                }
            }
        }
    }

    private fun navToLoginFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_logInFragment)
    }

    private fun navToEventFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_eventsFragment)
    }
}