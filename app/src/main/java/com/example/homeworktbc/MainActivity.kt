package com.example.usersapp

import User
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworktbc.databinding.ActivityMainBinding
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        users.addAll(
            listOf(
                User(1, "გრიშა", "ონიანი", "1724647601641", "სტალინის სახლმუზეუმი", "grisha@mail.ru"),
                User(2, "Jemal", "Kakauridze", "1714647601641", "თბილისი, ლილოს მიტოვებული ქარხანა", "jemal@gmail.com")
            )
        )

        binding.searchBtn.setOnClickListener {
            val searchText = binding.search.text.toString()
            val user = searchUser(searchText)

            if (user != null) {
                binding.result.text = "User: ${user.firstName} ${user.lastName} - Birthday: ${(user.birthday)}"
                binding.result.visibility = View.VISIBLE
                binding.btnAddUser.visibility = View.GONE
            } else {
                binding.result.text = "User not found"
                binding.result.visibility = View.VISIBLE
                binding.btnAddUser.visibility = View.VISIBLE
            }
        }

        binding.btnAddUser.setOnClickListener {
            val intent = Intent(this, AddUser::class.java)
            startActivityForResult(intent, ADD_USER_REQUEST_CODE)
        }
    }

    private fun searchUser(searchText: String): User? {
        return users.find {
            it.firstName.contains(searchText) ||
                    it.lastName.contains(searchText)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_USER_REQUEST_CODE && resultCode == RESULT_OK) {
            val newUser = data?.getParcelableExtra<User>("newUser")
            newUser?.let {
                users.add(it)
                Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val ADD_USER_REQUEST_CODE = 1
    }
}
