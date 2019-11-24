package com.km.ticktock.views.alarmsetting

import androidx.lifecycle.MutableLiveData
import com.km.ticktock.base.BaseViewModel

class SettingSecondViewModel : BaseViewModel() {
    val sourceClickEvent = MutableLiveData<Boolean>()
    val destinationClickEvent = MutableLiveData<Boolean>()

    fun sourceClick() {
        sourceClickEvent.value = true
    }

    fun destinationClick() {
        destinationClickEvent.value = true
    }
}