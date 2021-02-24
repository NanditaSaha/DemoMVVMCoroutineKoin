package com.example.demodatabindingwithcoroutine.data.api

import com.example.demodatabindingwithcoroutine.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiHelper {
    @GET("users")
    suspend fun getUserAsync(): Response<List<User>>
}