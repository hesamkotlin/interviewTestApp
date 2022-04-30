package com.example.interviewtestapp.shared.model

import com.google.gson.annotations.SerializedName

data class UserInfo(

    @SerializedName("first_name")
    val firstname: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("region")
    val region: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("coordinate_mobile")
    val coordinateMobile: String,

    @SerializedName("coordinate_phone_number")
    val coordinatePhoneNumber: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("lat")
    val lat: Double,

    @SerializedName("lng")
    val lng: Double,

    )