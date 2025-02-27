package com.example.homeworktbc.presentation.settings

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homeworktbc.databinding.FragmentSettingsBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.settings.effect.SettingsEffect
import com.example.homeworktbc.presentation.settings.event.SettingsEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val viewModel: SettingsViewModel by viewModels()

    override fun start() {
        setupLanguageSpinner()
        observeViewModel()
    }

    private fun setupLanguageSpinner() {
        val spinner: Spinner = binding.languageSpinner
        val languages = listOf("English", "ქართული")  // List of languages
        val languageCodes = mapOf("English" to "en", "ქართული" to "ka")  // Mapping of languages to codes

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // When the save button is clicked, save the selected language to DataStore
        binding.saveButton.setOnClickListener {
            val selectedLanguage = spinner.selectedItem.toString()
            val languageCode = languageCodes[selectedLanguage] ?: "en"
            viewModel.obtainEvent(SettingsEvent.LanguageSelected(languageCode))  // Save the language selection
        }
    }

    private fun observeViewModel() {
        // Observe changes in the view state to update the spinner's selected language
        lifecycleScope.launch {
            viewModel.viewState.collect { state ->
                val selectedPosition = if (state.selectedLanguage == "ka") 1 else 0
                binding.languageSpinner.setSelection(selectedPosition)  // Set spinner selection based on saved language
            }
        }

        // Handle any effects emitted from the ViewModel
        lifecycleScope.launch {
            viewModel.effects.collect { effect ->
                when (effect) {
                    is SettingsEffect.RestartApp -> restartApp()  // Restart the app if necessary
                }
            }
        }
    }

    private fun restartApp() {
        requireActivity().recreate()  // Restart the activity
    }
}