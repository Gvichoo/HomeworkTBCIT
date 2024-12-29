package com.example.homeworktbc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.databinding.FragmentStatusBinding


class StatusFragment : Fragment() {
    private lateinit var binding: FragmentStatusBinding
    private lateinit var selectedItem: Item

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStatusBinding.bind(view)

        selectedItem = arguments?.getParcelable("selectedItem") ?: return

        updateStatus()

        binding.btnToDelivered.setOnClickListener {
            updateItemStatus("DELIVERED")
        }

        binding.btnToCanceled.setOnClickListener {
            updateItemStatus("CANCELED")
        }
    }

    private fun updateStatus() {
        binding.apply {
            tvStatus.text = selectedItem.status

            tvStatus.setTextColor(
                when (selectedItem.status) {
                    "DELIVERED" -> android.graphics.Color.GREEN
                    "CANCELED" -> android.graphics.Color.RED
                    else -> android.graphics.Color.GRAY
                }
            )
        }
    }

    private fun updateItemStatus(newStatus: String) {
        selectedItem.status = newStatus

        updateStatus()

        findNavController().navigate(R.id.mainFragment)
    }
}