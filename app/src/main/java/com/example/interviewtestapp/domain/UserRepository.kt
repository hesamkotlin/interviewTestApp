package com.example.interviewtestapp.domain

import com.example.interviewtestapp.data.remote.NetworkDataSource
import com.example.interviewtestapp.data.remote.Resource
import com.example.interviewtestapp.shared.model.User
import com.example.interviewtestapp.shared.model.UserInfo
import com.example.interviewtestapp.shared.model.mapToUser
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) {
    suspend fun getUserList(): Resource<List<User>> {
        return networkDataSource.getUserList()
    }

    suspend fun setUserInfo(
        userInfo: UserInfo
    ): Resource<User> {
        return networkDataSource.setUserInfo(userInfo)
    }

}

