package com.km.ticktock.views.alarmsetting.data

import com.km.ticktock.views.alarmsetting.domain.SettingRepository1
import io.reactivex.Observable
import java.util.*

class SettingRepositoryImpl1: SettingRepository1 {

    private fun setHour(hour: Int): Int {
        if (hour < 0) return 12
        else if (hour > 12) return 0
        else return hour
    }

    override fun setHourText(hour: Int) = Observable.fromCallable {
        setHour(hour).toString()
    }

    private fun setMinute(minute: Int): Int {
        if (minute < 0) return 59
        else if (minute > 59) return 0
        else return minute
    }

    override fun setMinuteText(minute: Int) = Observable.fromCallable {
        setMinute(minute).toString()
    }

    override fun nextStepClickable(pq: PriorityQueue<Int>) = Observable.fromCallable {
        !pq.isEmpty()
    }
}