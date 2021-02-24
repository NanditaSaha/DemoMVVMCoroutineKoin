package com.example.demodatabindingwithcoroutine.ui.home

import com.example.demodatabindingwithcoroutine.data.api.ApiHelper
import com.example.demodatabindingwithcoroutine.data.model.User
import retrofit2.Response

class HomeRepository(private val apiHelper: ApiHelper) {
    suspend fun getUserAsync(): Response<List<User>>{
        return apiHelper.getUserAsync()
    }
}