package com.example.homeworktbc.presentation.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.domain.repository.DataStoreRepository
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.profile.effect.ProfileEffect
import com.example.homeworktbc.presentation.settings.effect.SettingsEffect
import com.example.homeworktbc.presentation.settings.event.SettingsEvent
import com.example.homeworktbc.presentation.settings.state.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<SettingsState, SettingsEvent, SettingsEffect>(SettingsState()) {

    override fun obtainEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SaveLanguage -> {
                viewModelScope.launch {
                    saveLanguage(event.languageCode)
                }
            }

            SettingsEvent.LogOut -> logout()
        }
    }

    private fun logout() {
        updateState { copy(isLoggingOut = true) }
        viewModelScope.launch {
            dataStoreRepository.removeByKey(PreferenceKeys.email)
            updateState { copy(isLoggingOut = false) }
            emitEffect(SettingsEffect.NavigateToLogin)

        }
    }

    private suspend fun saveLanguage(language: String) {
        dataStoreRepository.saveLanguage(language)
        updateState { copy(selectedLanguage = language) }
        val locale = Locale(language)
        val localeList = LocaleListCompat.forLanguageTags(language)
        Locale.setDefault(locale)
        AppCompatDelegate.setApplicationLocales(localeList)

        emitEffect(SettingsEffect.ShowLanguageChangeMessage("Language changed to $language"))
    }

    fun loadSavedLanguage() {
        viewModelScope.launch {
            dataStoreRepository.readLanguage().collect { language ->
                updateState { copy(selectedLanguage = language) }
            }
        }
    }
}
