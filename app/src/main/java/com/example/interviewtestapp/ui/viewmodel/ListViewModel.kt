package com.example.interviewtestapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewtestapp.R
import com.example.interviewtestapp.data.remote.Resource
import com.example.interviewtestapp.domain.UserRepository
import com.example.interviewtestapp.shared.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val mList = MutableLiveData<List<User>>()
    val list: LiveData<List<User>> = mList

    private val mLoading = MutableLiveData(false)
    val loading: LiveData<Boolean> = mLoading

    private val mMessage = MutableLiveData<Int>()
    val message: LiveData<Int> = mMessage

    fun getUSerList() {
        viewModelScope.launch(Dispatchers.IO) {
            mLoading.postValue(true)
            when (val result = repository.getUserList()) {
                is Resource.Loading -> {
                    Log.d("hesam", "Loading")
                }
                is Resource.Success -> {
                    mLoading.postValue(false)
                    Log.d("hesam", "the request has been successful")
                    Log.d("hesam", result.data.toString())
                    mList.postValue(result.data)
                }
                is Resource.Failure -> {
                    mMessage.postValue(R.string.server_error)
                    Log.d("hesam", result.exception.toString())
                }

            }
        }
    }
}

