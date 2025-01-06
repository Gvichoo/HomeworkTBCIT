package com.example.homeworktbc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var viewBinding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentStartBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnStartGame.setOnClickListener {
            val selectedSize = when {
                viewBinding.checkbox3x3.isChecked -> 3
                viewBinding.checkbox4x4.isChecked -> 4
                viewBinding.checkbox5x5.isChecked -> 5
                else -> null
            }

            selectedSize?.let {
                val bundle = Bundle().apply {
                    putInt("Size", it)
                }
                findNavController().navigate(R.id.action_startFragment_to_gameFragment, bundle)
            } ?: run {
                Toast.makeText(requireContext(), "Please select size", Toast.LENGTH_SHORT).show()
            }
        }
    }
}