package com.example.interviewtestapp.ui.util

import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.interviewtestapp.R


fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, action: (t: T) -> Unit) {
    liveData?.observe(this) { t -> action(t) }
}

fun <T> Fragment.observe(liveData: LiveData<T>?, action: (t: T) -> Unit) {
    viewLifecycleOwner.observe(liveData, action)
}

fun Fragment.showToast(message: String) {
    val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
    val toastLayout = toast.view as LinearLayout?
    val toastTV = toastLayout!!.getChildAt(0) as TextView
    val font = ResourcesCompat.getFont(requireContext(), R.font.lalezar)
    toastTV.typeface = font
    toast.show()

}