package com.example.homeworktbc.presentation.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import kotlinx.coroutines.flow.collect
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.domain.usecase.readLanguage.ReadLanguageUseCase
import com.example.homeworktbc.domain.usecase.removeKey.RemoveKeyUseCase
import com.example.homeworktbc.domain.usecase.saveLanguage.SaveLanguageUseCase
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.settings.effect.SettingsEffect
import com.example.homeworktbc.presentation.settings.event.SettingsEvent
import com.example.homeworktbc.presentation.settings.state.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val removeKeyUseCase: RemoveKeyUseCase,
    private val saveLanguageUseCase: SaveLanguageUseCase,
    private val readLanguageUseCase: ReadLanguageUseCase
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
        if (viewState.value.isLoggingOut) return

        updateState { copy(isLoggingOut = true) }

        viewModelScope.launch {
            removeKeyUseCase(PreferenceKeys.email)
            updateState { copy(isLoggingOut = false) }
            emitEffect(SettingsEffect.NavigateToLogin)
        }
    }



    private suspend fun saveLanguage(language: String) {
        saveLanguageUseCase(language)
        updateState { copy(selectedLanguage = language) }
        val locale = Locale(language)
        val localeList = LocaleListCompat.forLanguageTags(language)
        Locale.setDefault(locale)
        AppCompatDelegate.setApplicationLocales(localeList)

        emitEffect(SettingsEffect.ShowLanguageChangeMessage("Language changed to $language"))
    }

    fun loadSavedLanguage() {
        viewModelScope.launch {
            readLanguageUseCase().collect { language ->
                updateState { copy(selectedLanguage = language) }
            }
        }
    }

}
