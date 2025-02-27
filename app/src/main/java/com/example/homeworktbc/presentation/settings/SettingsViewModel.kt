package com.example.homeworktbc.presentation.settings

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.di.repository.DataStoreRepository
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.settings.effect.SettingsEffect
import com.example.homeworktbc.presentation.settings.event.SettingsEvent
import com.example.homeworktbc.presentation.settings.state.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<SettingsState, SettingsEvent, SettingsEffect>(SettingsState()) {

    // Initialize with default language if none is saved
    init {
        loadSavedLanguage() // Load the language initially if any value exists in DataStore
    }

    override fun obtainEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.LanguageSelected -> saveLanguage(event.language)
            is SettingsEvent.LoadSavedLanguage -> loadSavedLanguage()
        }
    }

    private fun saveLanguage(language: String) {
        viewModelScope.launch {
            dataStoreRepository.saveLanguage(language)  // Save the selected language
            updateState { copy(selectedLanguage = language) }
            emitEffect(SettingsEffect.RestartApp)  // Emit effect to restart the app (optional)
        }
    }

    private fun loadSavedLanguage() {
        viewModelScope.launch {
            dataStoreRepository.readLanguage().collect { savedLanguage ->
                updateState { copy(selectedLanguage = savedLanguage) }  // Update state with saved language
            }
        }
    }
}
