package com.example.homeworktbc

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homeworktbc.databinding.ActivityFinalBinding

class Final : AppCompatActivity() {
    private lateinit var binding : ActivityFinalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBackward.setOnClickListener {
            toStart()
        }
        val username = intent.getStringExtra("Username")
        binding.usernameTV.text = username

    }
    private fun toStart(){
        val intent = Intent(this, MainActivity ::class.java)
        startActivity(intent)
    }
}