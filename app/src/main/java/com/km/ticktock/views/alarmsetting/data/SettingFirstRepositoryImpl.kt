package com.km.ticktock.views.alarmsetting.data

import com.km.ticktock.views.alarmsetting.domain.SettingFirstRepository
import io.reactivex.Observable

class SettingFirstRepositoryImpl: SettingFirstRepository {

    private fun setHour(hour: Int): Int {
        if (hour < 0) return 0
        else if (hour > 12) return 12
        else return hour
    }

    override fun setHourText(hour: Int) = Observable.fromCallable {
        setHour(hour).toString()
    }

    private fun setMinute(minute: Int): Int {
        if (minute < 0) return 0
        else if (minute > 60) return 60
        else return minute
    }

    override fun setMinuteText(minute: Int) = Observable.fromCallable {
        setMinute(minute).toString()
    }
}