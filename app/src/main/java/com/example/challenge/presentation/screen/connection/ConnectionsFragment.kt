package com.example.challenge.presentation.screen.connection

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge.R
import com.example.challenge.presentation.base.BaseFragment
import com.example.challenge.databinding.FragmentConnectionsBinding
import com.example.challenge.presentation.extension.collect
import com.example.challenge.presentation.extension.collectLatest
import com.example.challenge.presentation.screen.connection.event.ConnectionEvent
import com.example.challenge.presentation.extension.showSnackBar
import com.example.challenge.presentation.screen.connection.effect.ConnectionEffect
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class ConnectionsFragment :
    BaseFragment<FragmentConnectionsBinding>(FragmentConnectionsBinding::inflate) {

    private val viewModel: ConnectionsViewModel by viewModels()
    private val connectionsRecyclerAdapter by lazy { ConnectionsRecyclerAdapter() }

    override fun start() {
        observeState()
        observeEffect()
        bind()
        btnLogOutClickListener()
    }



    private fun observeState(){
        collect(viewModel.viewState){ state ->
            binding.loaderInclude.loaderContainer.visibility =
                if (state.isLoading) View.VISIBLE else View.GONE

            state.connections.let {
                connectionsRecyclerAdapter.submitList(it)
            }
            state.errorMessage?.let {
                binding.root.showSnackBar(message = it)
                viewModel.obtainEvent(ConnectionEvent.ResetErrorMessage)
            }
        }
    }

    private fun observeEffect(){
        collectLatest(viewModel.effects){effect ->
            when(effect){
                ConnectionEffect.NavigateToLogIn -> navigateToLogin()
            }
        }
    }



    private fun bind() {
        binding.apply {
            recyclerConnections.layoutManager = LinearLayoutManager(requireContext())
            recyclerConnections.setHasFixedSize(true)
            recyclerConnections.adapter = connectionsRecyclerAdapter
        }
        viewModel.obtainEvent(ConnectionEvent.FetchConnections)
    }

    private fun navigateToLogin(){
        findNavController().navigate(R.id.action_connectionsFragment_to_logInFragment)
    }

    private fun btnLogOutClickListener(){
        viewModel.obtainEvent(ConnectionEvent.LogOut)
    }


//    fun bindObserves() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.connectionState.collect {
//                    handleConnectionState(state = it)
//                }
//            }
//        }
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.effects.collect {
//                    handleNavigationEvents(event = it)
//                }
//            }
//        }
//    }

//    private fun handleConnectionState(state: ConnectionState) {
//        binding.loaderInclude.loaderContainer.visibility =
//            if (state.isLoading) View.VISIBLE else View.GONE
//
//        state.connections?.let {
//            connectionsRecyclerAdapter.submitList(it)
//        }
//
//        state.errorMessage?.let {
//            binding.root.showSnackBar(message = it)
//            viewModel.onEvent(ConnectionEvent.ResetErrorMessage)
//        }
//    }

}
