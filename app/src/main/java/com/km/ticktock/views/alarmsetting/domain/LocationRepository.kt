package com.km.ticktock.views.alarmsetting.domain

import android.content.Context
import com.km.ticktock.views.alarmsetting.entity.SearchPubTransPath
import io.reactivex.Observable
import java.util.*

interface LocationRepository {
    fun showToast(context: Context)

    fun getTransPath(): Observable<ArrayList<SearchPubTransPath>>
}