package com.example.usersapp

import User
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworktbc.databinding.ActivityAddUserBinding


class AddUser : AppCompatActivity() {

    private lateinit var binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val newUser = User(
                id = System.currentTimeMillis().toInt(),
                firstName = binding.firstName.text.toString(),
                lastName = binding.lastName.text.toString(),
                birthday = binding.birthday.text.toString(),
                address = binding.address.text.toString(),
                email = binding.email.text.toString()
            )

            val resultIntent = Intent()
            resultIntent.putExtra("newUser", newUser)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
