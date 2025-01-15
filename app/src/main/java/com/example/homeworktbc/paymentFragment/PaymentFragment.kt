package com.example.homeworktbc.paymentFragment

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homeworktbc.R
import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.dataClasses.Card
import com.example.homeworktbc.dataClasses.CardTypes
import com.example.homeworktbc.databinding.FragmentPaymentBinding
import com.example.homeworktbc.deleteCardFragment.DeleteCardFragment


class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding :: inflate) {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val cardList = mutableListOf(
        Card(cardNumber = 1234567812345678, cardHolderName = "Saba Gvichiani", valid = "12/34", card = CardTypes.VISA_CARD),
        Card(cardNumber = 8765432187654321, cardHolderName = "Saba Gvichiani", valid = "14/24", card = CardTypes.MASTER_CARD)
    )
    private val args : PaymentFragmentArgs by navArgs()


    override fun start() {

        viewPagerAdapter = ViewPagerAdapter()
        binding.viewPager.adapter = viewPagerAdapter
        viewPagerAdapter.submitList(cardList)

        viewPagerAdapter.setOnItemLongClickListener { position ->
            if (position < cardList.size) {
                val card = cardList[position]
                showDeleteCardBottomSheet(card)
            }
        }

        //Add the passed card to the list
        args.card?.let {
            cardList.add(it)
            //Update list
            viewPagerAdapter.submitList(cardList)
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.addCardFragment)
        }
        navigateToAddFragment()

        binding.btnBack1.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun navigateToAddFragment() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.addCardFragment)
        }
    }



    private fun showDeleteCardBottomSheet(card: Card) {
        val deleteCardFragment = DeleteCardFragment()

        deleteCardFragment.setCardToDelete(card)
        deleteCardFragment.setOnCardDeleteListener { cardToDelete ->
            deleteCard(cardToDelete)
        }
        deleteCardFragment.show(childFragmentManager, deleteCardFragment.tag)
    }

    private fun deleteCard(cardToDelete: Card) {
        val newList = cardList.toMutableList()
        newList.remove(cardToDelete)

        viewPagerAdapter.submitList(newList)
    }

}
