package com.km.ticktock.views.alarmsetting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.km.ticktock.base.BaseViewModel
import com.km.ticktock.services.RetrofitService
import com.km.ticktock.views.alarmsetting.model.KeywordObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel : BaseViewModel() {
    val searchText = MutableLiveData<String>()
    val locationList = MutableLiveData<ArrayList<KeywordObject.documents>>().apply {
        value = arrayListOf()
    }

    fun getKeywordSearch(keyword: String) {
        registerDisposable(RetrofitService.restAPI().keywordSearch(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    locationList.value = result.documents
                },
                { err ->
                    Log.e("Error User", err.toString())
                }
            )
        )
    }
}