package com.example.homeworktbc.paymentFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.homeworktbc.dataClasses.Card
import com.example.homeworktbc.databinding.CardsLayoutBinding


class ViewPagerAdapter : ListAdapter<Card, ViewPagerAdapter.ViewPagerHolder>(CardDiffUtil()) {

    private var onItemLongClickListener: ((Int) -> Unit)? = null


    fun setOnItemLongClickListener(listener: (Int) -> Unit) {
        onItemLongClickListener = listener
    }
    inner class ViewPagerHolder(private val binding: CardsLayoutBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            binding.ivCard.setImageResource(card.card.img)
            binding.cardNumber.text = card.cardNumber.toString()
            binding.cardHolderName.text = card.cardHolderName
            binding.tvValid.text = card.valid
            binding.cardNumber.text = formatCardNumber(card.cardNumber)
        }
        private fun formatCardNumber(cardNumber: Long): String {
            val cardString = cardNumber.toString()
            val formatted = StringBuilder()

            for (i in cardString.indices) {
                formatted.append(cardString[i])
                if (i % 4 == 3) {
                    formatted.append("  ")
                } else {
                    formatted.append(" ")
                }
            }

            if (formatted.isNotEmpty()) {
                formatted.deleteCharAt(formatted.length - 1)
            }

            return formatted.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding = CardsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val card = getItem(position)
        holder.bind(card)

        holder.itemView.setOnLongClickListener {
            onItemLongClickListener?.invoke(position)
            true
        }

    }

    override fun getItemCount(): Int = currentList.size

    class CardDiffUtil : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }
}