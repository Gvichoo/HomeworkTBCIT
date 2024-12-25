package com.example.homeworktbc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.CategoryButtonsBinding

class CategoryAdapter(
    private val categories: List<Categories>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryButtonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory()
    }
    private var selectedCategoryPosition: Int = categories.find {
        it.name.equals("all", ignoreCase = true)
    }?.let { categories.indexOf(it) } ?: 0


    inner class CategoryViewHolder(private val binding: CategoryButtonsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindCategory() {
            val currentCategory = categories[adapterPosition]
            binding.button.apply {
                this.text = currentCategory.name
                this.setCompoundDrawablesWithIntrinsicBounds(currentCategory.image ?: 0, 0, 0, 0)
                this.isSelected = adapterPosition == selectedCategoryPosition
                this.setOnClickListener {
                    val prevPosition = selectedCategoryPosition
                    selectedCategoryPosition = adapterPosition
                    notifyItemChanged(prevPosition)
                    notifyItemChanged(selectedCategoryPosition)
                    onClick(currentCategory.name)
                }
            }
        }

    }
}
