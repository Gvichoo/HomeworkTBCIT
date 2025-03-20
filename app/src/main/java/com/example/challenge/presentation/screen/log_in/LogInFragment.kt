package com.example.challenge.presentation.screen.log_in

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.challenge.R
import com.example.challenge.presentation.base.BaseFragment
import com.example.challenge.databinding.FragmentLogInBinding
import com.example.challenge.presentation.extension.collect
import com.example.challenge.presentation.extension.collectLatest
import com.example.challenge.presentation.screen.log_in.event.LogInEvent
import com.example.challenge.presentation.extension.showSnackBar
import com.example.challenge.presentation.screen.log_in.effect.LoginEffect
import com.example.challenge.presentation.screen.log_in.state.LogInState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val viewModel: LogInViewModel by viewModels()


    override fun start() {
        observeState()
        observeEffect()
        logIn()
    }



    private fun observeState(){
        collect(viewModel.viewState){ state ->
            binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            state.errorMessage?.let {
                binding.root.showSnackBar(message = it)
                viewModel.onEvent(LogInEvent.ResetErrorMessage)
            }
        }
    }


    private fun observeEffect(){
        collectLatest(viewModel.effects){
            when(it){
                LoginEffect.NavigateToConnections -> {
                    Log.d("LogInFragment", "Navigating to connections fragment")
                    navigateToConnectionsFragment()
                }
            }
        }
    }

    private fun navigateToConnectionsFragment(){
        findNavController().navigate(R.id.action_logInFragment_to_connectionsFragment)
    }

    private fun logIn(){
        binding.btnLogIn.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            Log.d("LogInFragment", "Nope or Yes")

            viewModel.obtainEvent(LogInEvent.LogIn(email,password))
        }
    }
}
