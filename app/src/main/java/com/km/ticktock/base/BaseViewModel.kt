package com.km.ticktock.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel: ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun registerDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun registerDisposables(vararg  disposables: Disposable) {
        compositeDisposable.addAll(*disposables)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}