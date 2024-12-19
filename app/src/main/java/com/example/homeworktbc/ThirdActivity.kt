package com.example.homeworktbc

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworktbc.databinding.ActivityThirdBinding
import com.google.firebase.auth.FirebaseAuth

class ThirdActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityThirdBinding
    private  lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()


        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.signUp.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        binding.signIn.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (validateInput(email, password)) {
                signInUser(email, password)
            }

        }


    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this,"Invalid Email!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()) {
            Toast.makeText(this,"Invalid password!",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun signInUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    binding.email.text.clear()
                    binding.password.text.clear()
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, FinalActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "User does not exist!", Toast.LENGTH_SHORT).show()
                }
            }
    }


}