package com.km.ticktock.views.alarmsetting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.km.ticktock.base.BaseViewModel
import com.km.ticktock.views.alarmsetting.domain.SettingFirstRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SettingFirstViewModel(private val repository: SettingFirstRepository): BaseViewModel() {

    private val TAG = SettingFirstViewModel::class.java.name
    private val hourText = MutableLiveData<String>()
    private val minuteText = MutableLiveData<String>()

    init {
        requestHourText(12)
        requestMinuteText(30)
    }

    fun observeHourText() = hourText

    fun observeMinuteText() = minuteText

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

    private fun handleError(throwable: Throwable) {
        Log.e(TAG, throwable.message!!)
    }
}