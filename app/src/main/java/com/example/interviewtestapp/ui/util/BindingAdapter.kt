package com.example.interviewtestapp.ui.util

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter


@BindingAdapter("visibleGone")
fun visibleOrGone(view: View, visible: Boolean?) {
    visible ?: return
    view.isVisible = visible
}