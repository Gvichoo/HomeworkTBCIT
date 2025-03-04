package com.example.homeworktbc.presentation.add_event

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homeworktbc.data.room.entity.EventEntity
import com.example.homeworktbc.databinding.FragmentAddEventBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddEventFragment : BaseFragment<FragmentAddEventBinding>(FragmentAddEventBinding::inflate) {

    private val viewModel: AddEventViewModel by viewModels()

    override fun start() {
        binding.btnAddEvent.setOnClickListener {
            val name = binding.etEventName.text.toString()
            val organizer = binding.etOrganizer.text.toString()
            val date = binding.etDate.text.toString()
            val info = binding.etInfo.text.toString()
            val price = binding.etPrice.text.toString()
            val imageUrl = binding.etImageUrl.text.toString()

            if (name.isNotEmpty() && organizer.isNotEmpty() && date.isNotEmpty()) {
                val event = EventEntity(
                    name = name,
                    organizer = organizer,
                    date = date,
                    info = info,
                    price = price,
                    image = imageUrl
                )

//                lifecycleScope.launch {
//                    viewModel.insertEvent(event)
//                }
            }
        }
    }
}
