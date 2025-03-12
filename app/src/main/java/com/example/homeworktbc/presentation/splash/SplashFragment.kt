package com.example.homeworktbc.presentation.splash

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentSplashBinding
import com.example.homeworktbc.presentation.splash.effect.SplashEffect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun start() {
        binding.progressBar.visibility = View.VISIBLE
        observeState()
        observeEffects()
        splashViewModel.checkSession()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                splashViewModel.viewState.collect { state ->
                    binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                }
            }
        }
    }

    private fun observeEffects() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                splashViewModel.effects.collect { effect ->
                    when (effect) {
                        is SplashEffect.NavigateToHome -> navigateToHome()
                        is SplashEffect.NavigateToLogin -> navigateToLogin()
                    }
                }
            }
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_splashFragment_to_logInFragment)
    }
}
