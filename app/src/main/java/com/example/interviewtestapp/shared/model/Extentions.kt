package com.example.interviewtestapp.shared.model

import com.example.interviewtestapp.data.model.UsersResponse

fun UsersResponse.mapToUser(): User {
    return User(
        firstName = firstName,
        lastName = lastName,
        address = address,
        coordinate_mobile = coordinateMobile
    )
}
