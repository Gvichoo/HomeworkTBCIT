package com.example.homeworktbc.presentation.fragmentProfile

import android.util.Log
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.homeworktbc.R
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentProfileBinding
import com.example.homeworktbc.presentation.fragmentProfile.effect.ProfileEffect
import com.example.homeworktbc.presentation.fragmentProfile.event.ProfileEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun start() {
        observeState()
        observeEffects()
        logOutButtonClicked()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { state ->
                    if (state.savedEmail.isNotEmpty()) {
                        binding.tvEmail.text = state.savedEmail
                    }
                }
            }
        }
    }

    private fun observeEffects() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effects.collect { effect ->
                    when (effect) {
                        is ProfileEffect.NavigateToLogin -> {
                            Log.d("ProfileFragment", "NavigateToLogin effect triggered")
                            navToLogInFragment()
                        }
                    }
                }
            }
        }
    }
    private fun logOutButtonClicked(){
        binding.btnLogout.setOnClickListener{
            viewModel.obtainEvent(ProfileEvent.LogoutButtonClicked)
        }
    }

    private fun navToLogInFragment() {

        val navOptions = navOptions {
            popUpTo(R.id.logInFragment) {
                inclusive = true
            }
        }
        findNavController().navigate(R.id.action_profileFragment_to_logInFragment, null, navOptions)

    }
}







