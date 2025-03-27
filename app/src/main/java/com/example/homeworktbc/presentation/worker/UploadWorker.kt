package com.example.homeworktbc.presentation.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.homeworktbc.domain.core.Resource
import com.example.homeworktbc.domain.usecase.UploadImageUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.io.File

@HiltWorker
class UploadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val uploadImageUseCase: UploadImageUseCase,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val filePath = inputData.getString("file_path") ?: return Result.failure()
        val file = File(filePath)
        return when (uploadImageUseCase(file)) {
            is Resource.Error -> Result.failure()
            is Resource.Loading -> TODO()
            is Resource.Success -> Result.success()
        }
    }
}