package com.example.interviewtestapp.data.remote

import com.example.interviewtestapp.shared.model.User
import com.example.interviewtestapp.shared.model.UserInfo
import com.example.interviewtestapp.shared.model.mapToUser
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getUserList(): Resource.Success<List<User>>{
        return Resource.Success(webService.getUsersList().map { it.mapToUser() })
    }

    suspend fun setUserInfo(
        userInfo: UserInfo
    ): Resource.Success<User>{
        return Resource.Success(webService.setUserInfo(userInfo).mapToUser())
    }

}