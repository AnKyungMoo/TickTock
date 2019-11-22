package com.km.ticktock

import android.app.Application
import com.km.ticktock.utils.BasicUtils
import com.km.ticktock.views.alarmsetting.adapter.PathBindingAdapter
import com.km.ticktock.views.alarmsetting.adapter.SubPathBindingAdapter
import com.km.ticktock.views.main.adapter.MainBindingAdapter

class TickTockApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        BasicUtils.init(this)
        initBindingAdapter()
    }

    private fun initBindingAdapter() {

        MainBindingAdapter.init(this)
        PathBindingAdapter.init(this)
        SubPathBindingAdapter.init(this)
    }
}