package com.example.homeworktbc.presentation.profile

import androidx.lifecycle.viewModelScope
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.data.repository.LogInRepositoryImpl
import com.example.homeworktbc.domain.usecase.saveEmail.SaveValueUseCase
import com.example.homeworktbc.domain.usecase.session.GetSessionValueUseCase
import com.example.homeworktbc.presentation.baseviewmodel.BaseViewModel
import com.example.homeworktbc.presentation.profile.effect.ProfileEffect
import com.example.homeworktbc.presentation.profile.event.ProfileEvent
import com.example.homeworktbc.presentation.profile.state.ProfileState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getSessionValueUseCase: GetSessionValueUseCase,
) : BaseViewModel<ProfileState, ProfileEvent, ProfileEffect>(ProfileState()) {

//    fun loadEmail() {
//        viewModelScope.launch {
//            getSessionValueUseCase(PreferenceKeys.email).collect { savedEmail ->
//                updateState { copy(email = savedEmail) }
//            }
//        }
//    }

    fun loadEmail() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        updateState {
            // Update the state with the current user's email (or empty string if null)
            copy(email = currentUser?.email.orEmpty())
        }
    }



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