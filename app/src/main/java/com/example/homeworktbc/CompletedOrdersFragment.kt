package com.example.homeworktbc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.databinding.FragmentCompletedOrdersBinding


class CompletedOrdersFragment : Fragment() {

    private lateinit var binding: FragmentCompletedOrdersBinding
    private lateinit var adapter: CompletedFragmentsAdapter

    private val itemsList = listOf(
        Items2(
            name = "Modern Wingback Chair",
            image = R.drawable.chair,
            color = "red",
            price = "$280.00",
            qtyNum = 2
        ),
        Items2(
            name = "Chair",
            image = R.drawable.chair2,
            color = "BLue",
            price = "$500.00",
            qtyNum = 1
        ),
        Items2(
            name = "Chair ",
            image = R.drawable.chair,
            color = "red",
            price = "$150.00",
            qtyNum = 3
        ),
        Items2(
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
        binding = FragmentCompletedOrdersBinding.inflate(inflater, container, false)

        adapter = CompletedFragmentsAdapter()
        binding.rvActive.layoutManager = LinearLayoutManager(context)
        binding.rvActive.adapter = adapter

        adapter.submitList(itemsList)


        return binding.root
    }

}