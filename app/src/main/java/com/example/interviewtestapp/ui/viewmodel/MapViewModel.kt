package com.example.interviewtestapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewtestapp.R
import com.example.interviewtestapp.data.remote.Resource
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

    private val mMessage = MutableLiveData<Int>()
    val message: LiveData<Int> = mMessage

    private val mLoading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = mLoading

    private val mNavigateToListView = MutableLiveData<Unit>()
    val navigateToListFragment : LiveData<Unit> = mNavigateToListView

    private lateinit var mUserInfo: UserInfo
    private var mLatitude: Double? = null
    private var mLongitude: Double? = null

    fun setLocation(latitude: Double, longitude: Double) {
        mLatitude = latitude
        mLongitude = longitude
    }

    fun setUserInfo(userInfo: UserInfo) {
        mUserInfo = userInfo
        Log.d("hesam", mUserInfo.toString())
    }

    fun onSubmitLocationClicked() {
        val latitude = mLatitude
        val longitude = mLongitude
        if (latitude == null || longitude == null) {
            mMessage.value = R.string.location_empty_error
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            mLoading.postValue(true)
            val response = repository.setUserInfo(mUserInfo.also {
                it.lat = latitude
                it.lng = longitude
                Log.d("hesamm", mUserInfo.toString())
            })
            mLoading.postValue(false)
            if (response is Resource.Success){
                mMessage.postValue(R.string.data_submited_to_server)
                mNavigateToListView.postValue(Unit)

            }else{
                mMessage.postValue(R.string.server_error)
                Log.d("hesam", (response as Resource.Failure).exception.toString())
            }
        }
    }

}