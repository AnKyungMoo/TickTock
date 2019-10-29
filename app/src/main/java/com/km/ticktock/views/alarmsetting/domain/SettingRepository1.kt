package com.km.ticktock.views.alarmsetting.domain

import io.reactivex.Observable
import java.util.*

interface SettingRepository1 {

    fun setHourText(hour: Int): Observable<String>

    fun setMinuteText(minute: Int): Observable<String>

    fun nextStepClickable(pq: PriorityQueue<Int>): Observable<Boolean>
}