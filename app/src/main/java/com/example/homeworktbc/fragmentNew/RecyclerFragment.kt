package com.example.homeworktbc.fragmentNew

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.R
import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.databinding.FragmentRecyclerBinding
import com.example.homeworktbc.fragmentNew.data.User


class RecyclerFragment : BaseFragment<FragmentRecyclerBinding>(FragmentRecyclerBinding::inflate) {
    override fun start() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        val users = List(9) { index ->
            User(
                firstName = "FirstName $index",
                lastName = "LastName $index",
                email = "email$index@example.com",
                avatar = R.drawable.avatar
            )
        }

        val adapter = UserAdapter(users)
        binding.recyclerView.adapter = adapter
    }

}