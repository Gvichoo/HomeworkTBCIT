package com.example.homeworktbc.presentation.home

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.R
import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.databinding.FragmentHomeBinding
import com.example.homeworktbc.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val storyAdapter = StoryAdapter()
    private val postAdapter = PostAdapter()

    override fun start() {
        setupRecyclerStory()
        setupRecyclerPost()
        observeEvents()
        homeViewModel.getStories()
        homeViewModel.getPosts()
    }

    private fun setupRecyclerStory() {
        binding.recyclerStory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerStory.adapter = storyAdapter
    }
    private fun setupRecyclerPost() {
        binding.recyclerPost.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerPost.adapter = postAdapter
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.story.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            storyAdapter.submitList(resource.data)
                        }
                        is Resource.Failed -> {
                            binding.progressBar.visibility = View.GONE
                            showError(resource.message)
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.post.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.progressBar2.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar2.visibility = View.GONE
                            postAdapter.submitList(resource.data)
                        }
                        is Resource.Failed -> {
                            binding.progressBar2.visibility = View.GONE
                            showError(resource.message)
                        }
                    }
                }
            }
        }

    }

    private fun showError(message: String?) {
        Toast.makeText(requireContext(), message ?: getString(R.string.unknown_error), Toast.LENGTH_SHORT).show()
    }

}
