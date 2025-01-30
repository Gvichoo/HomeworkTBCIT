package com.example.homeworktbc.fragmentMain

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homeworktbc.MainViewModel
import com.example.homeworktbc.baseClass.BaseClass
import com.example.homeworktbc.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : BaseClass<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val mainViewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun start() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.displayedUserData.collectLatest { user ->
                user?.let {
                    binding.tvFirstName.text = it.firstName
                    binding.tvLastName.text = it.lastName
                    binding.tvEmail.text = it.email
                }
            }
        }

        binding.btnSave.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val email = binding.etEmail.text.toString()

            mainViewModel.updateValue(firstName, lastName, email)
        }

        binding.btnRead.setOnClickListener {
            mainViewModel.readData()
        }
    }
}


