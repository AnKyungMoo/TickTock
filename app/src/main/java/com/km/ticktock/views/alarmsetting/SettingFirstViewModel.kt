package com.km.ticktock.views.alarmsetting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.km.ticktock.base.BaseViewModel
import com.km.ticktock.utils.BasicUtils
import com.km.ticktock.views.alarmsetting.entity.DayType
import com.km.ticktock.views.alarmsetting.entity.TimeType
import java.util.*

class SettingFirstViewModel: BaseViewModel() {

    private val TAG = SettingFirstViewModel::class.java.name
    val hourText = MutableLiveData<String>()
    val minuteText = MutableLiveData<String>()
    val nextStepClickable = MutableLiveData<Boolean>()

    val backClickEvent = MutableLiveData<Boolean>()
    val dayClickEvent = MutableLiveData<DayType>()
    val timeClickEvent = MutableLiveData<TimeType>()
    val nextClickEvent = MutableLiveData<Boolean>()
    val closeKeyboardEvent = MutableLiveData<Boolean>()

    init {
        requestHourText(BasicUtils.getHour())
        requestMinuteText(BasicUtils.getMinute())
        requestNextStepClickable(PriorityQueue())
    }

    fun onBackClick() {
        backClickEvent.value = true
    }

    fun onDayClick(dayType: DayType) {
        dayClickEvent.value = dayType
    }

    fun onTimeClick(timeType: TimeType) {
        timeClickEvent.value = timeType
        when (timeType) {
            TimeType.HOUR_UP -> requestHourText(hourText.value!!.toInt()-1)
            TimeType.HOUR_DOWN -> requestHourText(hourText.value!!.toInt()+1)
            TimeType.MINUTE_UP -> requestMinuteText(minuteText.value!!.toInt()-1)
            TimeType.MINUTE_DOWN -> {
                if (minuteText.value!!.toInt() == 59) {
                    requestHourText(hourText.value!!.toInt()+1) // 시간도 같이 1 증가
                }
                requestMinuteText(minuteText.value!!.toInt()+1)
            }
            else -> {}
        }
    }

    fun onNextClick() {
        nextClickEvent.value = true
    }

    fun onCloseKeyboard() {
        closeKeyboardEvent.value = true
    }

    private fun setHour(hour: Int): Int {
        if (hour < 0) return 12
        else if (hour > 12) return 0
        else return hour
    }

    private fun setMinute(minute: Int): Int {
        if (minute < 0) return 59
        else if (minute > 59) return 0
        else return minute
    }

    fun requestHourText() {
        hourText.value = setHour(hourText.value!!.toInt()).toString()
    }

    private fun requestHourText(hour: Int) {
        hourText.value = setHour(hour).toString()
    }

    fun requestMinuteText() {
        minuteText.value = setMinute(minuteText.value!!.toInt()).toString()
    }

    private fun requestMinuteText(minute: Int) {
        minuteText.value = setMinute(minute).toString()
    }

    fun requestNextStepClickable(pq: PriorityQueue<Int>) {
        nextStepClickable.value = !pq.isEmpty()
    }

    private fun handleError(throwable: Throwable) {
        Log.e(TAG, throwable.message!!)
    }
}