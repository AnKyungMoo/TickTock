package com.km.ticktock.views.main.domain

import android.content.Context
import androidx.lifecycle.ViewModel

class MainViewModel(private val repo: MainRepository) : ViewModel() {

    fun funfun(context: Context) {
        repo.showToast(context)
    }
}