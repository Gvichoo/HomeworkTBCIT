package com.example.homeworktbc.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.di.repository.DataStoreRepository
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.profile.effect.ProfileEffect
import com.example.homeworktbc.presentation.profile.event.ProfileEvent
import com.example.homeworktbc.presentation.profile.state.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<ProfileState, ProfileEvent, ProfileEffect>(ProfileState()) {




    private fun logout() {
        updateState { copy(isLoggingOut = true) }
        viewModelScope.launch {
            dataStoreRepository.removeByKey(PreferenceKeys.email)
            updateState { copy(isLoggingOut = false) }
            emitEffect(ProfileEffect.NavigateToLogin)
        }
    }
    private fun goToSettings(){
        viewModelScope.launch {
            emitEffect(ProfileEffect.NavigateToSetting)
        }
    }

    override fun obtainEvent(event: ProfileEvent) {
        when(event){
            ProfileEvent.Logout -> logout()
            ProfileEvent.Settings -> goToSettings()
        }
    }

}