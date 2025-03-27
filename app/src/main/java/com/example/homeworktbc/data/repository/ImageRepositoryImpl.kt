package com.example.homeworktbc.data.repository

import android.net.Uri
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.repository.ImageRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage,
) : ImageRepository {
    override suspend fun uploadImage(file: File): Resource<Unit> {
        return try {
            val fileOfUri = Uri.fromFile(file)
            val storage = firebaseStorage.reference.child("images/${file.name}")
            storage.putFile(fileOfUri).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }
    }
}