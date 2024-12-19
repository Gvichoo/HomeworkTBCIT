package com.example.homeworktbc

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.homeworktbc.databinding.ActivityFinalBinding


class FinalActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityFinalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}