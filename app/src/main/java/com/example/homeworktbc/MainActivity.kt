package com.example.homeworktbc

import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homeworktbc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        text1()
    }
    fun text1(){
        binding.textView1.text = "Overview"
        binding.textView3.text = "8 hours"
        binding.celsiusET.text = "16  C"
        binding.textview4.text = "4.5"
        binding.textView6.text = "Details"
        binding.button.text = "Book Now"
        binding.textviewINFO.text ="This vast mountain range is renowned for its remarkable diversity in terms of topography and climate. It features towering peaks, active volcanoes, deep canyons, expansive plateaus, and lush valleys. The Andes are also home to "
        binding.Andes.text="Andes Mountain"
        binding.south.text = "South, America"
        binding.price.text = "Price"
        binding.dollar.text = "$230"
    }
}