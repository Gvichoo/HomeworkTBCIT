package com.example.homeworktbc.presentation.add_event

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.databinding.FragmentAddEventBinding
import com.example.homeworktbc.domain.modele.Event
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEventFragment : BaseFragment<FragmentAddEventBinding>(FragmentAddEventBinding::inflate) {
    override fun start() {
        binding.btnAddEvent.setOnClickListener {
            val eventName = binding.etEventName.text.toString()
            val organizer = binding.etOrganizer.text.toString()
            val date = binding.etDate.text.toString()
            val info = binding.etInfo.text.toString()
            val price = binding.etPrice.text.toString()
            val imageUrl = binding.etImageUrl.text.toString()

            if (eventName.isNotEmpty() && organizer.isNotEmpty() && date.isNotEmpty() &&
                info.isNotEmpty() && price.isNotEmpty() && imageUrl.isNotEmpty()) {

                val bundle = bundleOf(
                    "eventName" to eventName,
                    "organizer" to organizer,
                    "date" to date,
                    "info" to info,
                    "price" to price,
                    "image" to imageUrl
                )

                setFragmentResult("my_events", bundle)
                setFragmentResult("my_added_events", bundle)

                findNavController().popBackStack()
            }
        }

    }
}
