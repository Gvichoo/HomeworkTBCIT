package com.example.homeworktbc.fragment

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homeworktbc.UserAdapter
import com.example.homeworktbc.UserViewModel
import com.example.homeworktbc.baseClass.BaseClass
import com.example.homeworktbc.databinding.FragmentMainBinding
import kotlinx.coroutines.launch


class MainFragment : BaseClass<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private lateinit var userViewModel: UserViewModel

    override fun start() {
        userViewModel = UserViewModel()

        userViewModel.init(requireContext())

        userViewModel.fetchUsers(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.users.collect { users ->
                    binding.recyclerView.adapter = UserAdapter(users)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.loading.collect { isLoading ->
                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.error.collect { hasError ->
                    if (hasError) {
                        Toast.makeText(requireContext(), "Failed to load data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}