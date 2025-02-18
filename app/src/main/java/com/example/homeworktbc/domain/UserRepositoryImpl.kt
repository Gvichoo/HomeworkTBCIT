package com.example.homeworktbc.domain

import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override fun getUser(): String {
        return "Repository Data"
    }
}