package com.example.interviewtestapp.data.remote

import com.example.interviewtestapp.data.model.UsersResponse
import com.example.interviewtestapp.shared.model.UserInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {
    @GET("address")
    suspend fun getUsersList(): List<UsersResponse>

    @POST("address")
    suspend fun setUserInfo(
        @Body userInfo: UserInfo
    ): UsersResponse
}