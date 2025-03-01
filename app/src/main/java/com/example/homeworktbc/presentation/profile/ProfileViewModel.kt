package com.example.homeworktbc.presentation.profile

import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.profile.effect.ProfileEffect
import com.example.homeworktbc.presentation.profile.event.ProfileEvent
import com.example.homeworktbc.presentation.profile.state.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : BaseViewModel<ProfileState, ProfileEvent, ProfileEffect>(ProfileState()) {





    private fun goToSettings(){
        viewModelScope.launch {
            emitEffect(ProfileEffect.NavigateToSetting)
        }
    }

    override fun obtainEvent(event: ProfileEvent) {
        when(event){
            ProfileEvent.Settings -> goToSettings()
        }
    }

}