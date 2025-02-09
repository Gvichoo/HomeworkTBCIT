package com.example.homeworktbc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.PreferenceKeys
import com.example.DataStoreManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val email = DataStoreManager.readValue(PreferenceKeys.email)?.firstOrNull()
            val navController = findNavController(R.id.fragmentContainerView)
            if (!email.isNullOrEmpty()) {
                navController.navigate(R.id.homeFragment)
            } else {
                navController.navigate(R.id.logInFragment)
            }
        }

    }
}
