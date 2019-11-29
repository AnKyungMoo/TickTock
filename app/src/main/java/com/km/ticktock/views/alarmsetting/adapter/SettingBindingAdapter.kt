package com.km.ticktock.views.alarmsetting.adapter

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter

class SettingBindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("setClickable")
        fun setClickable(layout: ConstraintLayout, clickable: Boolean) {
            layout.apply {
                isClickable = clickable
                isFocusable = clickable
                isSelected = clickable
            }
        }
    }
}