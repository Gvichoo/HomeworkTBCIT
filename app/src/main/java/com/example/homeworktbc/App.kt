package com.example.homeworktbc

import android.app.Application
import android.content.Context
import com.example.homeworktbc.paging.Repository
import com.example.homeworktbc.paging.data.database.RoomDb
import com.example.homeworktbc.paging.data.retro.PagerRetrofit

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