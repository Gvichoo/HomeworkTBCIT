package com.example.homeworktbc.addCardFragment

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.dataClasses.Card
import com.example.homeworktbc.dataClasses.CardTypes
import com.example.homeworktbc.databinding.FragmentAddCardBinding


class AddCardFragment : BaseFragment<FragmentAddCardBinding>(FragmentAddCardBinding :: inflate) {

    private var selectedCardType: CardTypes = CardTypes.VISA_CARD

    override fun start() {

        binding.btnBack2.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.apply {
            ivCard.setImageResource(R.drawable.visa)

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.btnVisa -> {
                        selectedCardType = CardTypes.VISA_CARD
                        ivCard.setImageResource(R.drawable.visa)
                    }
                    R.id.btnMaster -> {
                        selectedCardType = CardTypes.MASTER_CARD
                        ivCard.setImageResource(R.drawable.master)
                    }
                }
            }

            btnAddCard.setOnClickListener {
                if (etCardNumber.text.isNullOrEmpty() || etCardNumber.text.length !=16 || etCardHolder.text.isNullOrEmpty() ||etCardHolder.text.length <11
                    || etExpires.text.isNullOrEmpty() || etExpires.text.length != 5){
                    Toast.makeText(context, "Invalid Fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val card = Card(
                    cardNumber = etCardNumber.text.toString().toLong(),
                    cardHolderName = etCardHolder.text.toString(),
                    valid = etExpires.text.toString(),
                    card = selectedCardType
                )

                val action = AddCardFragmentDirections.actionAddCardFragmentToPaymentFragment(card)
                findNavController().navigate(action)
            }
        }
    }

}