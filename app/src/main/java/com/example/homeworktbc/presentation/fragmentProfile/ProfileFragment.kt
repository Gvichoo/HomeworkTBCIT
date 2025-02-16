package com.example.homeworktbc.presentation.fragmentProfile

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun start() {
        setFragmentResultListener("login_success_key") { _, bundle ->
            val email = bundle.getString("email")
            if (!email.isNullOrEmpty()) {
                binding.tvEmail.text = email
            }
        }

        observeEmail()

        binding.btnLogout.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.logout()
                findNavController().navigate(R.id.action_profileFragment_to_logInFragment)
            }
        }
    }

    private fun observeEmail() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.savedEmail.collect { savedEmail ->
                    if (savedEmail.isNotEmpty()) {
                        binding.tvEmail.text = savedEmail
                    }
                }
            }
        }
    }
}
