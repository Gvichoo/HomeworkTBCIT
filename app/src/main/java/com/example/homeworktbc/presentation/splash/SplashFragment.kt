package com.example.homeworktbc.presentation.splash

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.data.datastore.DataStoreRepositoryImpl
import com.example.homeworktbc.data.datastore.PreferenceKeys
import com.example.homeworktbc.R
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()

    override fun start() {
        binding.progressBar.visibility = View.VISIBLE
        checkSession()
    }
    private fun checkSession() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.checkSession { isLoggedIn ->
                    binding.progressBar.visibility = View.GONE
                    if (isLoggedIn) {
                        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                    } else {
                        findNavController().navigate(R.id.action_splashFragment_to_logInFragment)
                    }
                }
            }
        }
    }
}