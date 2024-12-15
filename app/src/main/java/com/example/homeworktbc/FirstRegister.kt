package com.example.homeworktbc

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homeworktbc.databinding.ActivityFirstRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class FirstRegister : AppCompatActivity() {
    private lateinit var binding: ActivityFirstRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding = ActivityFirstRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBackward1.setOnClickListener{
            toStart()
        }
        binding.btnNext.setOnClickListener {
            toSignUp()
        }
    }

    private fun toSignUp(){
        val email = binding.etEmailR.text.toString()
        val password = binding.pasword.text.toString()
        if (Patterns.EMAIL_ADDRESS.matcher(binding.etEmailR.text).matches() && binding.pasword.text.isNotEmpty() && binding.pasword.text.length > 6){
            firebaseAuth.createUserWithEmailAndPassword(email,password)
            Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SecondRegister::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Email or password is not matching!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun toStart(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}