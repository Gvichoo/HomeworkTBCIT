package com.example.homeworktbc.presentation.screen

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.homeworktbc.databinding.FragmentMainBinding
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.presentation.bottomSheets.AccountsFragment
import com.example.homeworktbc.presentation.extension.collect
import com.example.homeworktbc.presentation.screen.effect.MainEffect
import com.example.homeworktbc.presentation.screen.event.MainEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    override fun start() {

        observeEffect()
        setAccountTextViewClickListener()
        navToAccountsBottomSheetFragment()

    }

    private fun observeEffect(){
        collect(viewModel.effects){
            when(it){

                MainEffect.NavToBottomSheet -> {
                    Log.d("MainFragment", "NavToBottomSheet effect triggered")
                    navToAccountsBottomSheetFragment()
                }
            }
        }
    }

    private fun setAccountTextViewClickListener(){
        binding.accountsFrom.setOnClickListener {
            viewModel.obtainEvent(MainEvent.AccountClicked)
        }
    }

    private fun navToAccountsBottomSheetFragment() {
        val accountsBottomSheet = AccountsFragment()
        accountsBottomSheet.show(childFragmentManager, accountsBottomSheet.tag)
    }

}