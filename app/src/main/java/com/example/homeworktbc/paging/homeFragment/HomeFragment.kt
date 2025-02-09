package com.example.homeworktbc.paging.homeFragment

import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.R
import com.example.homeworktbc.base.BaseFragment
import com.example.homeworktbc.databinding.FragmentHomeBinding
import com.example.homeworktbc.paging.UserPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var pageViewModel: HomeViewModel
    private lateinit var adapter: UserPagingAdapter

    override fun start() {
        pageViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

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
                pageViewModel.users.collectLatest { pagingData ->
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