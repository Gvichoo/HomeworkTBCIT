package com.example.homeworktbc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener()
    }
    private fun listener(){
        binding.btnSaveAddress.setOnClickListener {
            val location = binding.etLocation.text.toString()
            val address = binding.etAddress.text.toString()

            if (location.isEmpty() || address.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newHome = Home(
                id = id,
                image = null,
                title = location,
                address = address
            )

            val bundle = Bundle().apply {
                putParcelable("newItem", newHome)
            }
            findNavController().navigate(R.id.homeFragment2, bundle)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
