package com.example.homeworktbc.presentation.screen.imageFragment.event

import android.graphics.Bitmap

sealed class ImageEvent {
    data class SaveImage(val bitmap: Bitmap) : ImageEvent()
}
