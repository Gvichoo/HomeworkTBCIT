package com.example.homeworktbc.presentation.add_event

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.databinding.FragmentAddEventBinding
import com.example.homeworktbc.domain.modele.Event
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import com.example.homeworktbc.presentation.event.EventViewModel
import com.example.homeworktbc.presentation.event.event.EventEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEventFragment : BaseFragment<FragmentAddEventBinding>(FragmentAddEventBinding::inflate) {

    private val eventViewModel: EventViewModel by viewModels()

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
                val newEvent = Event(
                    id = System.currentTimeMillis().toInt(),
                    name = name,
                    image = image,
                    organizer = organizer,
                    date = date,
                    info = info,
                    price = price
                )

                eventViewModel.obtainEvent(EventEvent.NewEventAdded(newEvent))
                findNavController().popBackStack()
            }
        }
    }
}
