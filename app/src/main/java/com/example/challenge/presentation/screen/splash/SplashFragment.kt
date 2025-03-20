package com.example.challenge.presentation.screen.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.challenge.R
import com.example.challenge.databinding.FragmentSplashBinding
import com.example.challenge.presentation.base.BaseFragment
import com.example.challenge.presentation.extension.collectLatest
import com.example.challenge.presentation.screen.splash.effect.SplashEffect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: SplashViewModel by viewModels()


    override fun start() {
        observeEffect()
    }

    private fun observeEffect(){
        collectLatest(viewModel.effects){
            when(it){
                SplashEffect.NavigateToConnections -> navigateToConnectionsFragment()
                SplashEffect.NavigateToLogIn -> navigateToLogInFragment()
            }
        }
    }

    private fun navigateToConnectionsFragment(){
        findNavController().navigate(R.id.action_splashFragment_to_connectionsFragment)
    }

    private fun navigateToLogInFragment(){
        findNavController().navigate(R.id.action_splashFragment_to_logInFragment)
    }

}
