package com.example.interviewtestapp.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interviewtestapp.R
import com.example.interviewtestapp.domain.UserRepository
import com.example.interviewtestapp.shared.model.Gender
import com.example.interviewtestapp.shared.model.User
import com.example.interviewtestapp.shared.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@SuppressLint("NullSafeMutableLiveData")
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val mUserInfoSuccess = MutableLiveData<User>()
    val userInfoSuccess: LiveData<User> = mUserInfoSuccess

    private val mUserInfoFailure = MutableLiveData<Int>()
    val userInfoFailure: LiveData<Int> = mUserInfoFailure

    private val mNavigateToMap = MutableLiveData<UserInfo>()
    val navigateToMap: LiveData<UserInfo> = mNavigateToMap

    val firstName = ObservableField<String>()
    val lastName = ObservableField<String>()
    val coordinateMobile = ObservableField<String>()
    val coordinatePhoneNumber = ObservableField<String>()
    val address = ObservableField<String>()
    private var gender = Gender.MALE

    fun setUserInfo() {
        val errorId = validateInputs()
        if (errorId != null) {
            mUserInfoFailure.value = errorId
        } else {
            mNavigateToMap.value = UserInfo(
                firstname = firstName.get()!!,
                lastName = lastName.get()!!,
                coordinateMobile = coordinateMobile.get()!!,
                coordinatePhoneNumber = coordinatePhoneNumber.get()!!,
                address = address.get()!!,
                gender = gender
            )
            Log.d(
                "authViewModel",
                "${this.firstName.get()} " +
                        "${this.lastName.get()} " +
                        "${coordinateMobile.get()}" +
                        " ${coordinatePhoneNumber.get()} " +
                        "${this.address.get()} ${gender.name}"
            )
        }
    }


    fun onMaleSelected() {
        gender = Gender.MALE
    }

    fun onFemaleSelected() {
        gender = Gender.FEMALE
    }

    private fun validateInputs(): Int? {
        val firstName = firstName.get()
        val lastName = lastName.get()
        val mobile = coordinateMobile.get()
        val phoneNumber = coordinatePhoneNumber.get()
        val address = address.get()

        return when {
            firstName.isNullOrEmpty() -> R.string.first_name_empty_error
            firstName.length < 2 -> R.string.first_name_length_error

            lastName.isNullOrEmpty() -> R.string.last_name_empty_error
            lastName.length < 2 -> R.string.last_name_length_error

            mobile.isNullOrEmpty() -> R.string.phone_number_empty_error
            mobile.length != 11 || !mobile.startsWith("09") -> R.string.phone_number_format_error

            phoneNumber.isNullOrEmpty() -> R.string.phone_number_empty_error
            phoneNumber.length != 11 || !phoneNumber.startsWith("0") -> R.string.phone_number_format_error

            address.isNullOrEmpty() -> R.string.address_empty_error

            else -> null
        }
    }
}