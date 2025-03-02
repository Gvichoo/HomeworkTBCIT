package com.example.homeworktbc.presentation.settings

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.homeworktbc.R
import com.example.homeworktbc.databinding.FragmentSettingsBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.settings.effect.SettingsEffect
import com.example.homeworktbc.presentation.settings.event.SettingsEvent
import com.example.homeworktbc.presentation.settings.state.SettingsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    private val viewModel: SettingsViewModel by viewModels()

    private lateinit var selectedLanguage: String

    override fun start() {

        observeState()

        observeEffects()

        saveLanguage()

        startLogOutClickListener()


    }

    private fun saveLanguage(){
        val languagesList = arrayListOf("ქართული", "English")

        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            languagesList
        )
        binding.languageSpinner.adapter = adapter

        lifecycleScope.launch {
            viewModel.viewState.collect { state ->
                selectedLanguage = state.selectedLanguage
                val languagePosition = when (state.selectedLanguage) {
                    "ka" -> 0
                    "en" -> 1
                    else -> 0
                }
                binding.languageSpinner.setSelection(languagePosition)
            }
        }

        binding.languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedLanguage = when (position) {
                    0 -> "ka"
                    1 -> "en"
                    else -> "en"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.saveButton.setOnClickListener {
            viewModel.obtainEvent(SettingsEvent.SaveLanguage(selectedLanguage))
        }

        viewModel.loadSavedLanguage()
    }

    private fun startLogOutClickListener() {
        binding.btnLogOut.setOnClickListener {
            viewModel.obtainEvent(SettingsEvent.LogOut)
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


    private var isNavigating = false

    private fun handleEffect(effect: SettingsEffect) {
        when (effect) {
            SettingsEffect.NavigateToLogin -> {
                try {
                    // Ensure the fragment manager is not already executing a transaction
                    if (childFragmentManager.isStateSaved) {
                        return // Do not perform any transaction if state is saved
                    }

                    // Perform the navigation safely
                    findNavController().navigate(
                        R.id.action_settingsFragment_to_logInFragment,
                        null,
                        navOptions {
                            popUpTo(R.id.nav_graph) { inclusive = true }
                            launchSingleTop = true
                        }
                    )
                } catch (e: IllegalStateException) {
                    // Handle any navigation error gracefully
                    e.printStackTrace()  // Or use a logging tool to track
                }
            }
            is SettingsEffect.ShowLanguageChangeMessage -> showLanguageChangeMessage(effect.message)
        }
    }







    private fun handleState(state: SettingsState) {
        binding.btnLogOut.isEnabled = !state.isLoggingOut
    }

    private fun showLanguageChangeMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
