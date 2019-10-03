package com.km.ticktock.views.alarmsetting

import android.util.Log
import androidx.lifecycle.ViewModel
import com.km.ticktock.services.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LocationViewModel : ViewModel() {
    private lateinit var subscription: Disposable

    fun getKeywordSearch(keyword: String) {
        subscription = RetrofitService.restAPI().keywordSearch(keyword)
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
    }
}