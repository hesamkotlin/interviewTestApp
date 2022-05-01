package com.example.interviewtestapp.shared.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserInfo(

    @SerializedName("first_name")
    val firstname: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("region")
    val region: String = "1",

    @SerializedName("address")
    val address: String,

    @SerializedName("coordinate_mobile")
    val coordinateMobile: String,

    @SerializedName("coordinate_phone_number")
    val coordinatePhoneNumber: String,

    @SerializedName("gender")
    val gender: Gender,

    @SerializedName("lat")
    var lat: Double? = null,

    @SerializedName("lng")
    var lng: Double? = null,

    ) : Parcelable