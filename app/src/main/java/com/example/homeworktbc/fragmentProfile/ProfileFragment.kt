package com.example.homeworktbc.fragmentProfile

import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun start() {
        setFragmentResultListener("login_success_key") { _, bundle ->
            val email = bundle.getString("email")
            if (email != null) {
                binding.tvEmail.text = email
            }
        }

        binding.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_logInFragment)
        }
    }

}