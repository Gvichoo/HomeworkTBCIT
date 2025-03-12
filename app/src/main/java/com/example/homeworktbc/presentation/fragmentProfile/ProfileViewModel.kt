package com.example.homeworktbc.presentation.fragmentProfile

import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.local.datastore.PreferenceKeys
import com.example.homeworktbc.domain.usecase.dataStore.ReadValueUseCase
import com.example.homeworktbc.domain.usecase.dataStore.RemoveByKeyUseCase
import com.example.homeworktbc.presentation.baseViewModel.BaseViewModel
import com.example.homeworktbc.presentation.fragmentProfile.effect.ProfileEffect
import com.example.homeworktbc.presentation.fragmentProfile.event.ProfileEvent
import com.example.homeworktbc.presentation.fragmentProfile.state.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val readValueUseCase: ReadValueUseCase,
    private val removeByKeyUseCase: RemoveByKeyUseCase
) : BaseViewModel<ProfileState, ProfileEvent, ProfileEffect>(ProfileState()) {

    init {
        loadSavedEmail()
    }

    private fun loadSavedEmail() {
        updateState { copy(isLoading = true) }

        viewModelScope.launch {
            readValueUseCase.invoke(PreferenceKeys.email).collect { email ->
                updateState { copy(savedEmail = email) }
                updateState { copy(isLoading = false) }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            removeByKeyUseCase.invoke(PreferenceKeys.email)
            emitEffect(ProfileEffect.NavigateToLogin)
        }
    }

    override fun obtainEvent(event: ProfileEvent) {
        when(event){
            ProfileEvent.LogoutButtonClicked -> logout()
        }
    }
}
