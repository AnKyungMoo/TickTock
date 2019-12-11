package com.km.ticktock.views.main

import androidx.lifecycle.MutableLiveData
import com.km.ticktock.base.BaseViewModel
import com.km.ticktock.views.alarmsetting.entity.DayType

class MainViewModel: BaseViewModel() {

    val dayClickEvent = MutableLiveData<DayType>()

    fun onDayClick(dayType: DayType) {
        dayClickEvent.value = dayType
    }
}