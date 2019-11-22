package com.km.ticktock.views.main.adapter

import android.content.Context
import android.content.res.Resources
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.km.ticktock.R

class MainBindingAdapter {

    companion object {

        private lateinit var resources: Resources

        fun init(context: Context) {
            resources = context.resources
        }

        @JvmStatic
        @BindingAdapter("registered_appointment")
        fun registeredAppointment(textView: TextView, size: Int) {
            textView.setText(resources.getString(R.string.main_tv_registered_appointment, size))
        }
    }
}