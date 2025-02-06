package com.example.homeworktbc.fragmentSuccessful

import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.base.BaseClass
import com.example.homeworktbc.databinding.FragmentSuccessfulBinding


class SuccessfulFragment : BaseClass<FragmentSuccessfulBinding>(FragmentSuccessfulBinding::inflate) {

    override fun start() {

    }

    override fun listeners() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_successfulFragment_to_mainFragment)
        }
    }
}