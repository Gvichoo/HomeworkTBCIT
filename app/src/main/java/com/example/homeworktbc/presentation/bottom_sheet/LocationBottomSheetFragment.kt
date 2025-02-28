package com.example.homeworktbc.presentation.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homeworktbc.data.api.Location
import com.example.homeworktbc.databinding.FragmentLocationBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LocationBottomSheetFragment(private val location: Location) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentLocationBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationBottomSheetBinding.inflate(inflater, container, false)

        binding.title.text = location.title
        binding.address.text = location.address
        return binding.root
    }
}
