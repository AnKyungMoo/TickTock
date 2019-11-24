package com.km.ticktock.views.alarmsetting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.km.ticktock.base.BaseViewModel
import com.km.ticktock.services.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel : BaseViewModel() {
    val searchtext = MutableLiveData<String>()

    fun getKeywordSearch(keyword: String) {
        /* TODO: recyclerView가 완성되면 데이터 넣자 */
        registerDisposable(
            RetrofitService.restAPI().keywordSearch(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        Log.d("keywordResult", result.documents[0].place_name)
                    },
                    { err ->
                        Log.e("Error User", err.toString())
                    }
                )
        )
    }
}