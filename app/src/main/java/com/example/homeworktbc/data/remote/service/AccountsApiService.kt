package com.example.homeworktbc.data.remote.service

import com.example.homeworktbc.data.remote.dto.AccountDto
import retrofit2.Response
import retrofit2.http.GET

interface AccountsApiService {
    @GET("v3/d689fe3e-6faf-446a-9896-c538de3449fa")
    fun getAccounts(): Response<List<AccountDto>>
}