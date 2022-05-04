package com.example.interviewtestapp.data.remote

import com.example.interviewtestapp.shared.model.User
import com.example.interviewtestapp.shared.model.UserInfo
import com.example.interviewtestapp.shared.model.mapToUser
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getUserList(): Resource<List<User>> {
        return try {
            val userResponseList = webService.getUsersList()
            Resource.Success(userResponseList.map { it.mapToUser() })
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    suspend fun setUserInfo(
        userInfo: UserInfo
    ): Resource<User> {
        return try {
            val userResponseList = webService.setUserInfo(userInfo)
            Resource.Success(userResponseList.mapToUser())
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

}