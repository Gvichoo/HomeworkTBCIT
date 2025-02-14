package com.example.homeworktbc.presentation.fragment

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.homeworktbc.R
import com.example.homeworktbc.base.BaseFragment
import com.example.homeworktbc.data.Resource
import com.example.homeworktbc.databinding.FragmentMainBinding
import com.example.homeworktbc.presentation.fragment.adapter.ViewPagerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter()
    }

    override fun start() {
        setupViewPager()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.serverResponse.collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> loader(true)
                        is Resource.Error -> {
                            loader(false)
                            Toast(result.message)
                        }
                        is Resource.Success -> {
                            loader(false)
                        }
                    }
                }
            }
        }
    }

    private fun loader(isLoading: Boolean) {
        val progressBar = binding.root.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = viewPagerAdapter
            offscreenPageLimit = 3
            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(40))
            setPageTransformer(transformer)
        }
    }
}
