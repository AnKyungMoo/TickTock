package com.km.ticktock.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager
import java.util.*

class BasicUtils {

    companion object {

        private lateinit var display: Display
        private lateinit var windowManager: WindowManager

        fun init(context: Context) {
            windowManager = context.getSystemService(Activity.WINDOW_SERVICE) as WindowManager
            display = windowManager.defaultDisplay
        }

        fun getWidth(): Int {
            val point = Point()
            display.getSize(point)
            return point.x - 90
        }

        fun getHour() = Calendar.getInstance().get(Calendar.HOUR)

        fun getMinute() = Calendar.getInstance().get(Calendar.MINUTE)

        fun getAMPM() = Calendar.getInstance().get(Calendar.AM_PM)
    }
}