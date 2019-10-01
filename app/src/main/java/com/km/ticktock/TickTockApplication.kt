package com.km.ticktock

import android.app.Application
import com.km.ticktock.utils.BasicUtils
import com.km.ticktock.utils.BindUtils

class TickTockApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        BasicUtils.init(this)
        BindUtils.init(this)
    }
}