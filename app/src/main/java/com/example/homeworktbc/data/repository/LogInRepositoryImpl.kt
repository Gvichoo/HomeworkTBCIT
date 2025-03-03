package com.example.homeworktbc.data.repository

import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.repository.LogInRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : LogInRepository {

    override fun login(email: String, password: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result.user != null))
        } catch (e: Exception) {
            emit(Resource.Failed("Login failed: ${e.message}"))
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}