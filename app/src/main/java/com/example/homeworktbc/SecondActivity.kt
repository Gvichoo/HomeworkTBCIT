package com.example.homeworktbc

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworktbc.databinding.ActivitySecondBinding
import com.google.firebase.auth.FirebaseAuth

class SecondActivity : AppCompatActivity() {
    private  lateinit var binding : ActivitySecondBinding
    private lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.signIn.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
        binding.signUp.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (validateInput(email, password)) {
                signUpUser(email, password)
            }
        }
    }
    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty() && password.isEmpty() || password.length < 6 ){
            Toast.makeText(this,"Fill the fields!",Toast.LENGTH_SHORT).show()
            return false
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this,"Invalid Email!",Toast.LENGTH_SHORT).show()
            return false
        }


        if (password.isEmpty() || password.length < 6) {
            Toast.makeText(this,"Invalid password!",Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun signUpUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    binding.email.text.clear()
                    binding.password.text.clear()

                    Toast.makeText(this, "Sign-up successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ThirdActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "User did not add!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}