package com.example.homeworktbc

import User
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworktbc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val users = mutableListOf<User>()
    private var activeUsers = 0
    private var removedUsers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addUserBtn.setOnClickListener {
            val firstname = binding.etFirstName.text.toString()
            val lastname = binding.etLastName.text.toString()
            val age = binding.etAge.text.toString()
            val email = binding.etEmail.text.toString()


            if (firstname.isEmpty() || lastname.isEmpty() || age.isEmpty() || email.isEmpty()) {
                showOperation("All fields must be filled!", Color.RED)
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showOperation("Invalid email!", Color.RED)
                return@setOnClickListener
            }
            val user = User(
                firstName = binding.etFirstName.text.toString(),
                lastName = binding.etLastName.text.toString(),
                age = binding.etAge.text.toString().toInt(),
                email = binding.etEmail.text.toString()
            )


            if (users.any { it.email == user.email }) {
                showOperation("User already exists!", Color.RED)
            } else {
                users.add(user)
                activeUsers++
                updateCounts()
                showOperation("User added successfully!", Color.GREEN)
            }
        }

        binding.removeUserBtn.setOnClickListener {
            val email = binding.etEmail.text.toString()

            val user = users.find { it.email == email }
            if (user != null) {
                users.remove(user)
                activeUsers--
                removedUsers++
                updateCounts()
                showOperation("User deleted successfully!", Color.GREEN)
            } else {
                showOperation("User does not exist!", Color.RED)
            }
        }

        binding.updateUserBtn.setOnClickListener {
            val email = binding.etEmail.text.toString()

            val user = users.find { it.email == email }
            if (user != null) {
                val updatedUser = User(
                    firstName = binding.etFirstName.text.toString(),
                    lastName = binding.etLastName.text.toString(),
                    age = binding.etAge.text.toString().toIntOrNull() ?: 0,
                    email = email
                )
                users[users.indexOf(user)] = updatedUser
                showOperation("User updated successfully!", Color.GREEN)
            } else {
                showOperation("User does not exist!", Color.RED)
            }
        }
    }

    private fun updateCounts() {
        binding.activeUsersTV.text = "Active Users: $activeUsers"
        binding.removedUsersTV.text = "Removed Users: $removedUsers"
    }

    private fun showOperation(outPut: String, color: Int) {
        binding.operation.text = outPut
        binding.operation.setTextColor(color)
    }
}

