package com.km.ticktock.views.alarmsetting.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.km.ticktock.views.alarmsetting.model.KeywordObject

@BindingAdapter ("setLocationList")
fun setLocationList(recyclerView: RecyclerView, data: ArrayList<KeywordObject.documents>) {
    with(recyclerView.adapter as SearchAdapter) {
        addLocations(data)
    }
}