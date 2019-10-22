package com.km.ticktock.views.alarmsetting.domain

import io.reactivex.Observable

interface SettingFirstRepository {

    fun setHourText(hour: Int): Observable<String>

    fun setMinuteText(minute: Int): Observable<String>
}