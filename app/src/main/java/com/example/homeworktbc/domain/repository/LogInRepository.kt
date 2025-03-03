package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.domain.core.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface LogInRepository {
    fun login(email: String, password: String): Flow<Resource<Boolean>>
    fun getCurrentUser(): FirebaseUser?
}