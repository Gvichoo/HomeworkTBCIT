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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun start() {
        observeEffects()
        startSettingsClickListener()
        setGmail()
    }

    private fun setGmail(){
        parentFragmentManager.setFragmentResultListener("loginResult", viewLifecycleOwner) { _, bundle ->
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