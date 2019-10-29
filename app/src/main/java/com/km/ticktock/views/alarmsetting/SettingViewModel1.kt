package com.km.ticktock.views.alarmsetting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.km.ticktock.base.BaseViewModel
import com.km.ticktock.utils.BasicUtils
import com.km.ticktock.views.alarmsetting.domain.SettingRepository1
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class SettingViewModel1(private val repository: SettingRepository1): BaseViewModel() {

    private val TAG = SettingViewModel1::class.java.name
    private val hourText = MutableLiveData<String>()
    private val minuteText = MutableLiveData<String>()
    private val nextStepClickable = MutableLiveData<Boolean>()

    init {
        requestHourText(BasicUtils.getHour())
        requestMinuteText(BasicUtils.getMinute())
        requestNextStepClickable(PriorityQueue())
    }

    fun observeHourText() = hourText

    fun observeMinuteText() = minuteText

    fun observeNextStepClickable() = nextStepClickable

    fun requestHourText(hour: Int) {
        registerDisposable(
            repository.setHourText(hour)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponseHourText, this::handleError)
        )
    }

    private fun handleResponseHourText(hour: String) {
        hourText.value = hour
    }

    fun requestMinuteText(minute: Int) {
        registerDisposable(
            repository.setMinuteText(minute)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponseMinuteText, this::handleError)
        )
    }

    private fun handleResponseMinuteText(minute: String) {
        minuteText.value = minute
    }

    fun requestNextStepClickable(pq: PriorityQueue<Int>) {
        registerDisposable(
            repository.nextStepClickable(pq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponseClickable, this::handleError)
        )
    }

    private fun handleResponseClickable(clickable: Boolean) {
        nextStepClickable.value = clickable
    }

    private fun handleError(throwable: Throwable) {
        Log.e(TAG, throwable.message!!)
    }
}