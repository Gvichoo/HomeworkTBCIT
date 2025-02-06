package com.example.homeworktbc.fragmentMain

import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.homeworktbc.R
import com.example.homeworktbc.base.BaseClass
import com.example.homeworktbc.databinding.FragmentMainBinding


class MainFragment : BaseClass<FragmentMainBinding>(FragmentMainBinding::inflate) {

    companion object{
        const val CORRECTNUMBER = "0934"
        val enteredPassCode = StringBuilder()
    }
    override fun start() {


    }
    override fun listeners() {
        binding.buttonOne.setOnClickListener {
            appendToPassCode("1")
            Log.d("MainFragment", "Entered passcode: $enteredPassCode")

        }

        binding.buttonTwo.setOnClickListener {
            appendToPassCode("2")
        }

        binding.buttonThree.setOnClickListener {
            appendToPassCode("3")
        }

        binding.buttonFour.setOnClickListener {
            appendToPassCode("4")
        }

        binding.buttonFive.setOnClickListener {
            appendToPassCode("5")
        }

        binding.buttonSix.setOnClickListener {
            appendToPassCode("6")
        }

        binding.buttonSeven.setOnClickListener {
            appendToPassCode("7")
        }

        binding.buttonEight.setOnClickListener {
            appendToPassCode("8")
        }

        binding.buttonNine.setOnClickListener {
            appendToPassCode("9")
        }

        binding.buttonZero.setOnClickListener {
            appendToPassCode("0")
        }

        binding.buttonDelete.setOnClickListener {
            if(enteredPassCode.isNotEmpty()){
                enteredPassCode.deleteCharAt(enteredPassCode.length-1)
                updatePassCode(enteredPassCode)
            }
        }
    }

    private fun appendToPassCode(digit : String){
        enteredPassCode.append(digit)
        updatePassCode(enteredPassCode)
        if (enteredPassCode.length == 4){
            checkSuccess(enteredPassCode)
        }
    }

    private fun checkSuccess(enteredPassCode: StringBuilder){
        if(enteredPassCode.length == 4){
            if (enteredPassCode.toString() == CORRECTNUMBER ){
                Toast.makeText(requireContext(),"Successfully Entered!",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_mainFragment_to_successfulFragment)
                resetPasswordField(enteredPassCode)
            }else{
                resetPasswordField(enteredPassCode)
                Toast.makeText(requireContext(),"Incorrect Password!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updatePassCode(enteredPassCode : StringBuilder){
        when(enteredPassCode.length){
            1 -> binding.one.setBackgroundResource(R.drawable.pass_green)
            2 -> binding.two.setBackgroundResource(R.drawable.pass_green)
            3 -> binding.three.setBackgroundResource(R.drawable.pass_green)
            4 -> binding.four.setBackgroundResource(R.drawable.pass_green)
        }
    }

    private fun resetPasswordField(enteredPassCode: StringBuilder){
        binding.one.setBackgroundResource(R.drawable.pass_grey)
        binding.two.setBackgroundResource(R.drawable.pass_grey)
        binding.three.setBackgroundResource(R.drawable.pass_grey)
        binding.four.setBackgroundResource(R.drawable.pass_grey)
        enteredPassCode.clear()
    }
}