package com.example.interviewtestapp.shared.model

import com.example.interviewtestapp.data.getModel.UsersResponse

fun List<UsersResponse>.mapToUser(): List<User>{
    return map {
        User(
            firstName = it.firstName,
            lastName = it.lastName,
            address = it.address,
            coordinate_mobile = it.coordinateMobile
        )
    }
}