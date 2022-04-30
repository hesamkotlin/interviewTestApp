package com.example.interviewtestapp.data.remote

import com.example.interviewtestapp.data.getModel.UsersResponse
import retrofit2.http.GET

interface WebService {
    @GET("address")
    suspend fun getUsersList(): List<UsersResponse>


}