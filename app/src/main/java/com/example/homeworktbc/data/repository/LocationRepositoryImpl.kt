package com.example.homeworktbc.data.repository

import com.example.homeworktbc.data.api.Location
import com.example.homeworktbc.data.api.LocationService
import com.example.homeworktbc.domain.LocationRepository
import retrofit2.Call
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationService: LocationService
) : LocationRepository {
    override fun getLocations(): Call<List<Location>> {
        return locationService.getLocations()
    }
}
