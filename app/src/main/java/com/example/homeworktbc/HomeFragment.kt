package com.example.homeworktbc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val addressAdapter by lazy { HomeAddressAdapter() }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var addresses = mutableListOf(
        Home(1, R.drawable.img_1, "Home", "SBI Building, street 3, Software Park"),
        Home(1, R.drawable.img, "Office", "SBI Building, street 3, Software Park"),
        Home(1, null, "Saba", "Varketili")
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
        listener()



        arguments?.let {
            val newHome = it.getParcelable<Home>("newItem") // Retrieve the Home object passed from AddFragment
            newHome?.let { newItem ->
                addresses.add(newItem)
                addressAdapter.submitList(addresses.toList())
            }
        }
    }

    private fun setUp() {
        binding.recyclerViewAddresses1.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewAddresses1.adapter = addressAdapter
        addressAdapter.submitList(addresses)

    }
    private fun listener(){
        binding.btnAddNewAddress.setOnClickListener {
            findNavController().navigate(R.id.AddFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
