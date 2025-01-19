package com.example.homeworktbc.fragmentLogin

import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.databinding.FragmentLogInBinding


class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    override fun start() {
        binding.etLogin.text
    }

}