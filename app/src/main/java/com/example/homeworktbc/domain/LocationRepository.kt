package com.example.homeworktbc.domain

import com.example.homeworktbc.data.api.Location
import retrofit2.Call

interface LocationRepository {
    fun getLocations(): Call<List<Location>>
}
