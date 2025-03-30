package com.example.homeworktbc.presentation.bottomSheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.homeworktbc.databinding.FragmentFromAccountsBottomSheetBinding
import com.example.homeworktbc.presentation.bottomSheets.adapter.CardItemAdapter
import com.example.homeworktbc.presentation.bottomSheets.effect.AccountEffect
import com.example.homeworktbc.presentation.bottomSheets.event.AccountEvent
import com.example.homeworktbc.presentation.extension.collect
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountsFragment : BottomSheetDialogFragment() {


    private var _binding: FragmentFromAccountsBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AccountsViewModel by viewModels()

    private val adapter by lazy {
        CardItemAdapter {
            viewModel.obtainEvent(AccountEvent.ItemClicked)
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFromAccountsBottomSheetBinding.inflate(inflater, container, false)

        setupRecyclerView()
        observeEffects()

        viewModel.obtainEvent(AccountEvent.FetchAccounts)

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recycler.adapter = adapter
    }

    private fun observeEffects(){
        collect(viewModel.effects){
            when(it){
                AccountEffect.NavToMainFragment -> dismiss()
                is AccountEffect.ShowMessage -> showMessage(it.message)
                is AccountEffect.UpdateAccounts -> {
                    adapter.submitList(it.accounts)
                }
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}