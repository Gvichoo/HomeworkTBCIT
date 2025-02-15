package com.example.homeworktbc.presentation.mainFragment

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.homeworktbc.data.resource.Results
import com.example.homeworktbc.databinding.FragmentMainBinding
import com.example.homeworktbc.presentation.PageAdapter
import com.example.homeworktbc.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()
    private val viewPagerAdapter by lazy { PageAdapter() }

    override fun start() {
        if (!mainViewModel.isDataLoaded()) {
            mainViewModel.fetchData()
        }
        observeData()
        setupViewPager()
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.response.collectLatest { result ->
                    when (result) {
                        is Results.Failed -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                        }
                        is Results.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Results.Success -> {
                            binding.progressBar.visibility = View.GONE
                            viewPagerAdapter.submitList(result.data)
                        }
                    }
                }
            }
        }
    }

    private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = viewPagerAdapter
            offscreenPageLimit = 3

            setPadding(100, 0, 100, 0)
            clipToPadding = false
            clipChildren = false

            val transformer = CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))

                addTransformer { page, position ->
                    val scaleFactor = 0.85f + (1 - abs(position)) * 0.15f
                    page.scaleY = scaleFactor
                    page.alpha = 0.5f + (1 - abs(position)) * 0.5f

                    val pageMarginPx = -40
                    when {
                        position < -1 -> {
                            page.translationX = -page.width.toFloat()
                        }
                        position <= 1 -> {
                            val offset = position * -pageMarginPx
                            page.translationX = offset
                        }
                        else -> {
                            page.translationX = page.width.toFloat()
                        }
                    }
                }
            }
            setPageTransformer(transformer)
        }
    }
}
