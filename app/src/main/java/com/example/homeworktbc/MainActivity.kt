package com.example.homeworktbc

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setContent {
//            UsersSocialAppTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                ) {
//                    val navController = rememberNavController()
//                    AppNavGraph(navController = navController)
//                }
//            }

    }
}