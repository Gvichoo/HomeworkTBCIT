package com.example.homeworktbc.presentation.fragmentHome

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.R
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentHomeBinding
import com.example.homeworktbc.presentation.adapter.UserPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()


    private lateinit var adapter: UserPagingAdapter


    override fun start() {


        setUpRecyclerView()

        profileButtonClicked()

        observeEvent()

        observeState()

    }

    private fun observeEvent(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.users.collectLatest { pagingData ->
                        adapter.submitData(pagingData)

                    }
                }
            }

        }
    }

    private fun observeState(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collectLatest { loadStates ->
                    binding.loader.isVisible = loadStates.refresh is LoadState.Loading
                }
            }
        }
    }


    private fun profileButtonClicked(){
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)

        }
    }


    private fun setUpRecyclerView() {
        adapter = UserPagingAdapter()
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = this@HomeFragment.adapter

        }
    }
}