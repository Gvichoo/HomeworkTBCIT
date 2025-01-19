package com.example.homeworktbc.fragmentWelcome

import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.databinding.FragmentWelcomeBinding


class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {

    override fun start() {
        binding.btnLogin.setOnClickListener{
            findNavController().navigate(R.id.logInFragment)
        }

        binding.btnRegister.setOnClickListener{
            findNavController().navigate(R.id.registerFragment)
        }
    }

}