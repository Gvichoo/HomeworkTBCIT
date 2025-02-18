package com.example.homeworktbc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworktbc.databinding.ActivityMainBinding
import com.example.homeworktbc.domain.UserViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = "12345"
        val userViewModel = userViewModelFactory.create(userId)

        binding.tvText.text = userViewModel.getUserName()
    }
}

@AndroidEntryPoint
class ClassB(val userId : String){
    @Inject
    lateinit var
}