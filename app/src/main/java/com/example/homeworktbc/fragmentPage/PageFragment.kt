package com.example.homeworktbc.fragmentPage

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.baseClass.BaseClass
import com.example.homeworktbc.databinding.FragmentPageBinding
import com.example.homeworktbc.paging.UserPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PageFragment : BaseClass<FragmentPageBinding>(FragmentPageBinding :: inflate) {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var adapter: UserPagingAdapter

    override fun start() {
        pageViewModel = ViewModelProvider(this)[PageViewModel::class.java]

        setUpRecyclerView()



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
            adapter = this@PageFragment.adapter

        }
    }

}