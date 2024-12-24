package com.example.homeworktbc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktbc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter
    private val imageData = listImages()
    private val categories = listCategories()
    private var selectedCategory: String = "All"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {
        val imagesLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        val categoriesLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        imageAdapter = ImageAdapter(imageData)
        binding.apply {
            rvImages.layoutManager = imagesLayoutManager
            rvImages.adapter = imageAdapter
            rvCategories.layoutManager = categoriesLayoutManager
            rvCategories.adapter = CategoryAdapter(categories = categories, onClick = { category -> onCategorySelected(category) })
        }
    }

    private fun onCategorySelected(category: String) {
        selectedCategory = category
        val filteredData = when {
            category.equals("All", ignoreCase = true) -> imageData
            else -> imageData.filter { it.categoryType.equals(category, ignoreCase = true) }
        }
        imageAdapter.images = filteredData
        imageAdapter.notifyDataSetChanged()
    }

    private fun listCategories(): List<Categories> {
        return listOf(
            Categories(1, null, "All"),
            Categories(2, R.drawable.party, "Party"),
            Categories(3, R.drawable.camping, "Camping"),
            Categories(4, null, "Bar"),
            Categories(5, null, "Birthday"),
            Categories(6, null, "Cafe"),
        )
    }

    private fun listImages(): List<Image> {
        return listOf(
            Image(
                id = 1,
                image = R.drawable.person1,
                title = "Chachebi",
                categoryType = "Party",
                price = 9999999,
            ),
            Image(
                id = 2,
                image = R.drawable.person2,
                title = "Lacoste",
                categoryType = "Camping",
                price = 156,
            ),
            Image(
                id = 3,
                image = R.drawable.person3,
                title = "Nike",
                categoryType = "Party",
                price = 1003,
            ),
            Image(
                id = 4,
                image = R.drawable.person4,
                title = "Adidas",
                categoryType = "Camping",
                price = 7457
            ),
            Image(
                id = 5,
                image = R.drawable.person1,
                title = "Puma",
                categoryType = "Bar",
                price = 23535
            ),
            Image(
                id = 6,
                image = R.drawable.person2,
                title = "NewBalance",
                categoryType = "Camping",
                price = 6547,
            ),
            Image(
                id = 7,
                image = R.drawable.person3,
                title = "LouisVuitton",
                categoryType = "Birthday",
                price = 1234,
            ),
            Image(
                id = 8,
                image = R.drawable.person4,
                title = "Zara",
                categoryType = "Camping",
                price = 1754723,
            ),
            Image(
                id = 9,
                image = R.drawable.person1,
                title = "Gucci",
                categoryType = "Cafe",
                price = 96768,
            ),
            Image(
                id = 10,
                image = R.drawable.person2,
                title = "Rolex",
                categoryType = "Birthday",
                price = 124523,
            ),
            Image(
                id = 11,
                image = R.drawable.person2,
                title = "Ralph Lauren",
                categoryType = "Camping",
                price = 1124623,
            ),
            Image(
                id = 12,
                image = R.drawable.person3,
                title = "North Face",
                categoryType = "Cafe",
                price = 765,
            ),
            Image(
                id = 13,
                image = R.drawable.person4,
                title = "Polo",
                categoryType = "Bar",
                price = 2346,
            ),
            Image(
                id = 14,
                image = R.drawable.person1,
                title = "Moncler",
                categoryType = "Bar",
                price = 6794,
            ),
            Image(
                id = 15,
                image = R.drawable.person2,
                title = "Levi",
                categoryType = "Camping",
                price = 23852,
            )
        )
    }
}
