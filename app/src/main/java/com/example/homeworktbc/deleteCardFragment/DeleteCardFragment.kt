package com.example.homeworktbc.deleteCardFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.homeworktbc.dataClasses.Card
import com.example.homeworktbc.databinding.FragmentDeleteCardBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DeleteCardFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDeleteCardBinding? = null
    private val binding get() = _binding!!

    private lateinit var cardToDelete: Card
    private lateinit var onCardDeleteListener: (Card) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnYes.setOnClickListener {
            onCardDeleteListener(cardToDelete)
            dismiss()
        }

        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }

    fun setCardToDelete(card: Card) {
        cardToDelete = card
    }

    //What happens after after confirming delete
    fun setOnCardDeleteListener(listener: (Card) -> Unit) {
        onCardDeleteListener = listener
    }

    override fun onStart() {
        super.onStart()

        val dialog = dialog
        val window = dialog?.window
        val layoutParams = window?.attributes

        layoutParams?.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams?.height = WindowManager.LayoutParams.MATCH_PARENT
        window?.attributes = layoutParams
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}