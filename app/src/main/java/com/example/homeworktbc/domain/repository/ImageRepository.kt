package com.example.homeworktbc.domain.repository

import com.example.homeworktbc.domain.core.Resource
import java.io.File

interface ImageRepository {
    suspend fun uploadImage(file: File): Resource<Unit>
}