package com.km.ticktock.views.main.data

import android.content.Context
import android.widget.Toast
import com.km.ticktock.views.main.domain.MainRepository

class MainRepositoryImpl : MainRepository {
    override fun showToast(context: Context) {
        Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show()
    }
}