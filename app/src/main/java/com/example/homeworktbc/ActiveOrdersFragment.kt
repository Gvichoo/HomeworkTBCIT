package com.example.homeworktbc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.databinding.FragmentActiveOrdersBinding

class ActiveOrdersFragment : Fragment() {

    private lateinit var binding: FragmentActiveOrdersBinding
    private lateinit var adapter: ActiveFragmentsAdapter

    private val itemsList = listOf(
        Items(
            name = "Modern Wingback Chair",
            image = R.drawable.chair,
            color = "red",
            price = "$280.00",
            qtyNum = 2
        ),
        Items(
            name = "Chair",
            image = R.drawable.chair2,
            color = "BLue",
            price = "$500.00",
            qtyNum = 1
        ),
        Items(
            name = "Chair ",
            image = R.drawable.chair,
            color = "red",
            price = "$150.00",
            qtyNum = 3
        ),
        Items(
            name = "Chair chair",
            image = R.drawable.chair2,
            color = "blue",
            price = "$200.00",
            qtyNum = 1
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActiveOrdersBinding.inflate(inflater, container, false)

        adapter = ActiveFragmentsAdapter()
        binding.rvActive.layoutManager = LinearLayoutManager(context)
        binding.rvActive.adapter = adapter

        adapter.submitList(itemsList)

        return binding.root
    }
}