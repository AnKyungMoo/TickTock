package com.km.ticktock.views.alarmsetting

import androidx.lifecycle.MutableLiveData
import com.km.ticktock.base.BaseViewModel

class SearchViewModel : BaseViewModel() {
    val searchtext = MutableLiveData<String>()
}