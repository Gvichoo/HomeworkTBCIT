package com.example.homeworktbc.presentation.fragmentProfile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.homeworktbc.R
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentProfileBinding
import com.example.homeworktbc.presentation.extension.collect
import com.example.homeworktbc.presentation.fragmentProfile.effect.ProfileEffect
import com.example.homeworktbc.presentation.fragmentProfile.event.ProfileEvent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun start() {
        observeState()
        observeEffects()
        logOutButtonClicked()
    }

    private fun observeState() {

        collect(viewModel.viewState){
            if (it.savedEmail.isNotEmpty()) {
                binding.tvEmail.text = it.savedEmail
            }
        }
    }

    private fun observeEffects() {
        collect(viewModel.effects){
            when (it) {
                is ProfileEffect.NavigateToLogin -> {
                    navToLogInFragment()
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







