package com.example.homeworktbc.presentation.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.homeworktbc.R

fun ImageView.loadImagesGlide(url: String, cornerRadius: Int = 0) {
    val request = Glide.with(this)
        .load(url)
        .error(R.drawable.ic_launcher_foreground)
        .placeholder(R.drawable.avatar)
    if (cornerRadius > 0) {
        request.transform(RoundedCorners(cornerRadius))
    }
    request.into(this)
}