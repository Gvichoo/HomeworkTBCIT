package com.example.homeworktbc.presentation.homeFragment

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.App
import com.example.homeworktbc.R
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentHomeBinding
import com.example.homeworktbc.presentation.homeFragment.adapter.UserPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory((requireActivity().application as App).userRepository)
    }

    private lateinit var adapter: UserPagingAdapter


    override fun start() {


        setUpRecyclerView()

        binding.btnProfile.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)

        }


        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.loader.isVisible = loadStates.refresh is LoadState.Loading
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collectLatest { pagingData ->
                    Log.d("PageFragment", "PagingData received: $pagingData")
                    adapter.submitData(pagingData)

                }
            }
        }
    }



    private fun setUpRecyclerView() {
        adapter = UserPagingAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = this@HomeFragment.adapter

        }
    }


}