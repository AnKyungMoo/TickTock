package com.km.ticktock

import android.app.Application
import com.km.ticktock.utils.BasicUtils
import com.km.ticktock.views.alarmsetting.adapter.PathBindingAdapter
import com.km.ticktock.views.alarmsetting.adapter.SubPathBindingAdapter

class TickTockApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        BasicUtils.init(this)
        initBindingAdapter()
    }

    private fun initBindingAdapter() {

        PathBindingAdapter.init(this)
        SubPathBindingAdapter.init(this)
    }
}