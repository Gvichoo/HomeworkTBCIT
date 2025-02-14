package com.example.homeworktbc.data

import android.health.connect.datatypes.ExerciseRoute

fun ServerResponse.toLocation(): ExerciseRoute.Location {
    return Location(
        id = this.id,
        cover = this.cover,
        price = this.price,
        title = this.title,
        location = this.location,
        reactionCount = this.reactionCount,
        rate = this.rate
    )
}