package com.example.homeworktbc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworktbc.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogIn.setOnClickListener {
            loginBTN()
        }
        binding.btnRegister.setOnClickListener {
            registerBTN()
        }

    }
    private fun loginBTN(){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
    private fun registerBTN(){
        val intent = Intent(this, FirstRegister::class.java)
        startActivity(intent)
    }


}