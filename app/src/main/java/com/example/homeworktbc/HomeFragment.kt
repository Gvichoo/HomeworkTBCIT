package com.example.homeworktbc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val addressAdapter by lazy { HomeAddressAdapter() }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var addresses = mutableListOf(
        Home(1, null, "Saba", "Varketili"),
        Home(2, null, "John", "Downtown"),
        Home(3, null, "Jane", "Midtown")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()

        arguments?.let {
            val location = it.getString("location")
            val address = it.getString("address")

            if (!location.isNullOrEmpty() && !address.isNullOrEmpty()) {
                val newHome = Home(addresses.size + 1, null, location, address)
                addresses.add(newHome)
                addressAdapter.submitList(addresses.toList()) // Create a new list for DiffUtil
            }
        }
    }

    private fun setUp() {
        binding.recyclerViewAddresses1.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewAddresses1.adapter = addressAdapter
        addressAdapter.submitList(addresses) // Populate initially
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
