package com.example.interviewtestapp.data.model

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("last_name")
    val lastName: String,

    @field:SerializedName("coordinate_mobile")
    val coordinateMobile: String

)