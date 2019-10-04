package com.km.ticktock.views.alarmsetting.adapter

import android.content.Context
import android.content.res.Resources
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.km.ticktock.R
import com.km.ticktock.views.alarmsetting.entity.SearchPubTransPath
import java.text.DecimalFormat

class PathBindingAdapter {

    companion object {

        private lateinit var resources: Resources
        private lateinit var decimal: DecimalFormat

        fun init(context: Context) {
            resources = context.resources
            decimal = DecimalFormat("###,###")
        }

        @JvmStatic
        @BindingAdapter("setPathList")
        fun setPathList(recyclerView: RecyclerView, transPaths: ArrayList<SearchPubTransPath>) {
            val adapter = recyclerView.adapter as PathAdapter
            adapter.addAll(transPaths)
        }

        @JvmStatic
        @BindingAdapter("setPathTotalTime")
        fun setPathTotalTime(textView: TextView, totalTime: Int) {

            textView.text = when (totalTime >= 60) {
                true -> {
                    val hour = totalTime / 60
                    val minute = totalTime % 60
                    resources.getString(R.string.path_tv_total_time, hour, minute)
                }
                false -> {
                    resources.getString(R.string.path_tv_total_time_minute, totalTime)
                }
            }
        }

        @JvmStatic
        @BindingAdapter("setPathTotalWalk")
        fun setPathTotalWalk(textView: TextView, totalWalk: Int) {
            textView.text = resources.getString(R.string.path_tv_total_walk, totalWalk)
        }

        @JvmStatic
        @BindingAdapter("setPathPayment")
        fun setPathPayment(textView: TextView, payment: Int) {
            textView.text = resources.getString(R.string.path_tv_payment, decimal.format(payment))
        }
    }
}