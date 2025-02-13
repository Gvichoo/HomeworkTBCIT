package com.example.homeworktbc

import android.app.Application
import android.content.Context
import com.example.homeworktbc.data.paging.repo.Repository
import com.example.homeworktbc.data.paging.database.RoomDb
import com.example.homeworktbc.data.paging.retro.PagerRetrofit
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    private val appDatabase by lazy { RoomDb.getInstance(this) }
    val userRepository by lazy { Repository(appDatabase, PagerRetrofit.retrofit) }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
    companion object{
        var context : Context? = null
    }
}