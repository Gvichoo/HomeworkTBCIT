package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.resource.Resource
import com.example.homeworktbc.di.repository.RegisterRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : RegisterRepository {

    override fun register(email: String, password: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Failed("Sign-up failed: ${e.message}"))
        }
    }
}