package com.example.homeworktbc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homeworktbc.databinding.ActivitySecondRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class SecondRegister : AppCompatActivity() {
    private lateinit var binding : ActivitySecondRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondRegisterBinding.inflate(layoutInflater)

        firebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        binding.btnBackward2.setOnClickListener {
            toRegisterFirst()
        }

        binding.signUp.setOnClickListener {
            setUsername()
        }

    }
    private fun toRegisterFirst(){
        val intent = Intent(this, FirstRegister::class.java)
        startActivity(intent)
    }

    private fun setUsername(){
        val username = binding.usernameET.text.toString()
        if (username.isNotEmpty() && username.length > 8){
            val intent = Intent(this , Login::class.java)
            intent.putExtra("Username" , username )
            startActivity(intent)
        }else{
            Toast.makeText(this, "Username does not match!", Toast.LENGTH_SHORT).show()
        }
    }

}