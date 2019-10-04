package com.km.ticktock.views.alarmsetting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.km.ticktock.base.BaseViewModel
import com.km.ticktock.services.RetrofitService
import com.km.ticktock.views.alarmsetting.domain.LocationRepository
import com.km.ticktock.views.alarmsetting.entity.SearchPubTransPath
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocationViewModel(private val locationRepository: LocationRepository) : BaseViewModel() {

    private val TAG = LocationViewModel::class.java.name

    private val transPathLiveData = MutableLiveData<ArrayList<SearchPubTransPath>>()

    init {
        observeTransPathApprove()
    }

    fun observeTransPath() = transPathLiveData

    fun getKeywordSearch(keyword: String) {

        registerDisposable(
            RetrofitService.restAPI().keywordSearch(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        Log.d("keywordResult", result.documents[0].place_name)
                    },
                    { err ->
                        Log.e("Error User",err.toString())
                    }
                )
        )
    }

    private fun observeTransPathApprove() {
        requestTransPath()
    }

    private fun requestTransPath() {
        registerDisposable(
            locationRepository.getTransPath()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handlingReceivedResponse, this::handlingError)
        )
    }

    private fun handlingReceivedResponse(transPaths: ArrayList<SearchPubTransPath>) {
        transPathLiveData.value = transPaths
    }

    private fun handlingError(throwable: Throwable) {
        Log.e(TAG, throwable.message!!)
    }
}