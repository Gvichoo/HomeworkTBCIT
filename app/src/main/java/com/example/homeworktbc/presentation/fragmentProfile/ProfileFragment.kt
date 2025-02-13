package com.example.homeworktbc.presentation.fragmentProfile

import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.data.datastore.DataStoreManager
import com.example.homeworktbc.R
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentProfileBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private lateinit var dataStoreManager: DataStoreManager


    override fun start() {
        setFragmentResultListener("login_success_key") { _, bundle ->
            val email = bundle.getString("email")
            if (email != null) {
                binding.tvEmail.text = email


            }
        }

        readSavedEmail()

        dataStoreManager = DataStoreManager

        binding.btnLogout.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                dataStoreManager.removeByKey(requireContext(), PreferenceKeys.email)
                findNavController().navigate(R.id.action_profileFragment_to_logInFragment)
            }
        }
    }

    private fun readSavedEmail() {
        CoroutineScope(Dispatchers.Main).launch {
            dataStoreManager.readValue<String>(PreferenceKeys.email)?.collect { savedEmail ->
                if (savedEmail.isNotEmpty()) {
                    binding.tvEmail.text = savedEmail
                }
            }
        }
    }
}