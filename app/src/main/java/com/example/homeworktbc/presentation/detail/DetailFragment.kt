package com.example.homeworktbc.presentation.detail

import com.bumptech.glide.Glide
import com.example.homeworktbc.databinding.FragmentDetailBinding
import com.example.homeworktbc.presentation.base_fragment.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
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

    }

}