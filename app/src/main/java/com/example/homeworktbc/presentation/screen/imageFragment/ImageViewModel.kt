package com.example.homeworktbc.presentation.screen.imageFragment

import android.graphics.Bitmap
import com.example.homeworktbc.presentation.base.BaseViewModel
import com.example.homeworktbc.presentation.screen.imageFragment.event.ImageEvent
import com.example.homeworktbc.presentation.screen.imageFragment.state.ImageState

class ImageViewModel : BaseViewModel<ImageState, ImageEvent, Unit>(ImageState()) {


    override fun obtainEvent(event: ImageEvent) {
        when(event){
            is ImageEvent.SaveImage -> saveImage(event.bitmap)
        }
    }

    private fun saveImage(bitmap: Bitmap) {
        updateState { copy(image = bitmap) }
    }

}
