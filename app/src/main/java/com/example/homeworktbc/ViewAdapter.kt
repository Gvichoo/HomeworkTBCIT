package com.example.homeworktbc

import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.ChooserItemBinding
import com.example.homeworktbc.databinding.EditTextItemBinding
import com.example.homeworktbc.models.FieldItem

class ViewAdapter(private val onTextChanged: (Int, String?) -> Unit) :
    ListAdapter<FieldItem, RecyclerView.ViewHolder>(FieldItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TEXT_INPUT -> TextInputViewHolder(EditTextItemBinding.inflate(inflater, parent, false))
            else -> SpinnerViewHolder(ChooserItemBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SpinnerViewHolder -> holder.bindData(position)
            is TextInputViewHolder -> holder.bindData(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).fieldType == "input") TEXT_INPUT else SPINNER_CHOOSER
    }

    inner class SpinnerViewHolder(private val binding: ChooserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(position: Int) {
            val fieldItem = getItem(position)


            binding.spinner.visibility = if (fieldItem.isActive) View.VISIBLE else View.GONE
        }
    }

    inner class TextInputViewHolder(private val binding: EditTextItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(position: Int) {
            val fieldItem = getItem(position)

            binding.etEditTxt.apply {
                hint = fieldItem.hint
                inputType = when (fieldItem.keyboard) {
                    "text" -> InputType.TYPE_CLASS_TEXT
                    "number" -> InputType.TYPE_CLASS_NUMBER
                    else -> InputType.TYPE_NULL
                }

                visibility = if (fieldItem.isActive) View.VISIBLE else View.GONE

                doAfterTextChanged {
                    onTextChanged(fieldItem.fieldId, it.toString())
                }

                if (position == itemCount - 1) {
                    background = null
                }
            }
        }
    }

    companion object {
        private const val TEXT_INPUT = 1
        private const val SPINNER_CHOOSER = 2

        val FieldItemDiffCallback = object : DiffUtil.ItemCallback<FieldItem>() {
            override fun areItemsTheSame(oldItem: FieldItem, newItem: FieldItem): Boolean {
                return oldItem.fieldId == newItem.fieldId
            }

            override fun areContentsTheSame(oldItem: FieldItem, newItem: FieldItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
