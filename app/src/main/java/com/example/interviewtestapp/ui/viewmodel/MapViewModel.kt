package com.example.interviewtestapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewtestapp.R
import com.example.interviewtestapp.domain.UserRepository
import com.example.interviewtestapp.shared.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val mFailure = MutableLiveData<Int>()
    val failure: LiveData<Int> = mFailure

    private val mLoading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = mLoading

    private lateinit var mUserInfo: UserInfo
    private var mLatitude: Double? = null
    private var mLongitude: Double? = null

    fun setLocation(latitude: Double, longitude: Double) {
        mLatitude = latitude
        mLongitude = longitude
    }

    fun setUserInfo(userInfo: UserInfo) {
        mUserInfo = userInfo
    }

    fun onSubmitLocationClicked() {
        val latitude = mLatitude
        val longitude = mLongitude
        if (latitude == null || longitude == null) {
            mFailure.value = R.string.location_empty_error
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            mLoading.postValue(true)
            val response = repository.setUserInfo(mUserInfo.also {
                it.lat = latitude
                it.lng = longitude
            })
            mLoading.postValue(false)
        }
    }

}