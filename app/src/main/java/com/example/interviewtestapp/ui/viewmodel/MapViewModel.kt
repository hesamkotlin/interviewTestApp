package com.example.interviewtestapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.interviewtestapp.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private var mLatitude: Double? = null
    private var mLongitude: Double? = null

    fun setLocation(latitude: Double, longitude: Double) {
        mLatitude = latitude
        mLongitude = longitude
    }

}