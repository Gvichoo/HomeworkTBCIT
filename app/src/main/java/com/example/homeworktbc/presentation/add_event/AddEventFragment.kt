package com.example.homeworktbc.presentation.add_event

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.databinding.FragmentAddEventBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment

class AddEventFragment : BaseFragment<FragmentAddEventBinding>(FragmentAddEventBinding::inflate) {
    override fun start() {
        binding.btnAddEvent.setOnClickListener {
            val name = binding.etEventName.text.toString()
            val image = binding.etImageUrl.text.toString()
            val organizer = binding.etOrganizer.text.toString()
            val date = binding.etDate.text.toString()
            val info = binding.etInfo.text.toString()
            val price = binding.etPrice.text.toString()

            if (name.isNotEmpty() && image.isNotEmpty() && organizer.isNotEmpty() &&
                date.isNotEmpty() && info.isNotEmpty() && price.isNotEmpty()
            ) {
                val result = bundleOf(
                    "id" to System.currentTimeMillis().toInt(),
                    "name" to name,
                    "image" to image,
                    "organizer" to organizer,
                    "date" to date,
                    "info" to info,
                    "price" to price
                )

                parentFragmentManager.setFragmentResult("new_event", result)

                findNavController().popBackStack()
            }
        }
    }
}
