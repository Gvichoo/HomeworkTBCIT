package com.example.homeworktbc.splash

import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.datastore.DataStoreManager
import com.example.homeworktbc.datastore.PreferenceKeys
import com.example.homeworktbc.R
import com.example.homeworktbc.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun start() {
       checkSession()
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun checkSession() {
        CoroutineScope(Dispatchers.Main).launch {
            val email = DataStoreManager.readValue<String>(PreferenceKeys.email)?.firstOrNull()

            binding.progressBar.visibility = View.GONE

            if (!email.isNullOrEmpty()) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_logInFragment)
            }
        }
    }
}