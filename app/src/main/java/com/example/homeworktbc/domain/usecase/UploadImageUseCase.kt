package com.example.homeworktbc.domain.usecase

import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.repository.ImageRepository
import java.io.File
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(private val imageRepository: ImageRepository) {
    suspend operator fun invoke(file: File): Resource<Unit> {
        return imageRepository.uploadImage(file)
    }
}