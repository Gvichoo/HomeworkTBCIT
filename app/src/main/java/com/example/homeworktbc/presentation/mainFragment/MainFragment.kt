package com.example.homeworktbc.presentation.mainFragment

import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.databinding.FragmentMainBinding
import com.example.homeworktbc.presentation.baseFragment.BaseFragment
import com.example.homeworktbc.presentation.extension.collect
import com.example.homeworktbc.presentation.extension.collectLatest
import com.example.homeworktbc.presentation.mainFragment.adapter.MainAdapter
import com.example.homeworktbc.presentation.mainFragment.effect.MainEffect
import com.example.homeworktbc.presentation.mainFragment.event.MainEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()
    private val adapter: MainAdapter by lazy { MainAdapter() }

    override fun start() {

        observeState()
        observeEffect()
        fetchData()
        setUpRecycler()
        setUpSearchListener()

    }

    private fun setUpRecycler() {
        binding.rvCategory.layoutManager = LinearLayoutManager(context)
        binding.rvCategory.adapter = adapter
    }


    private fun observeState() {
        collect(viewModel.viewState) {
            binding.progressBar.visibility = if (it.isLoading) View.VISIBLE else View.GONE
            adapter.submitList(it.categories)
        }

    }

    private fun observeEffect() {
        collectLatest(viewModel.effects) {
            when (it) {
                is MainEffect.ShowMessage -> showMessage(it.message)
            }
        }

    }

    private fun fetchData() {
        viewModel.obtainEvent(MainEvent.FetchCategories)
    }

    private fun setUpSearchListener() {
        binding.etSearch.addTextChangedListener { text ->
            viewModel.obtainEvent(MainEvent.FilterCategories(text.toString()))
        }
    }


    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}