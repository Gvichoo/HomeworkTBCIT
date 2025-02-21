package com.example.homeworktbc.presentation.login

import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.databinding.FragmentLogInBinding
import com.example.homeworktbc.presentation.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth


class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private lateinit var firebaseAuth : FirebaseAuth
    override fun start() {
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_RegisterFragment)

            firebaseAuth = FirebaseAuth.getInstance()
            binding.btnSignIn

        }
    }



}