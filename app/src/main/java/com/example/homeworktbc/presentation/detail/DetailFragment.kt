package com.example.homeworktbc.presentation.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.homeworktbc.databinding.FragmentDetailBinding
import com.example.homeworktbc.domain.modele.AttendedEvent
import com.example.homeworktbc.presentation.base_fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val detailViewModel : DetailViewModel by viewModels()


    override fun start() {


        val args = DetailFragmentArgs.fromBundle(requireArguments())

        binding.apply {
            name.text = args.name
            organizer.text = args.organizer
            date.text = args.date
            info.text = args.info
            price.text = args.price

            Glide.with(requireContext())
                .load(args.image)
                .into(image)
        }

        binding.btnBuyTicket.setOnClickListener{
            val attendedEvent = AttendedEvent(
                id = args.id,
                name = args.name,
                organizer = args.organizer,
                date =  args.date,
                info = args.info,
                price = args.price,
                image = args.image
            )
            detailViewModel.insertAttendedEvent(attendedEvent)
            findNavController().popBackStack()
        }
    }

}