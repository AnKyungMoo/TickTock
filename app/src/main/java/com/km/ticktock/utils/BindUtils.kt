package com.km.ticktock.utils

import android.content.Context
import android.content.res.Resources
import com.km.ticktock.R
import com.km.ticktock.views.location.entity.SearchPubTransPath
import java.text.DecimalFormat

class BindUtils {

    companion object {

        private lateinit var resources: Resources
        private lateinit var decimal: DecimalFormat

        fun init(context: Context) {
            resources = context.resources
            decimal = DecimalFormat("###,###")
        }

        @JvmStatic
        fun setPathTotalTime(totalTime: Int) = when (totalTime >= 60) {
            true -> {
                val hour = totalTime / 60
                val minute = totalTime % 60
                resources.getString(R.string.path_tv_total_time, hour, minute)
            }
            false -> resources.getString(R.string.path_tv_total_time_minute, totalTime)
        }

        @JvmStatic
        fun setPathTotalWalk(totalWalk: Int)
                = resources.getString(R.string.path_tv_total_walk, totalWalk)

        @JvmStatic
        fun setPathPayment(payment: Int)
                = resources.getString(R.string.path_tv_payment, decimal.format(payment))

        @JvmStatic
        fun setSubPath(subPath: SearchPubTransPath.SubPath, isFirst: Boolean, isLast: Boolean) = when(isFirst) {
            true -> {
                var path = resources.getString(R.string.path_tv_get_on, subPath.startName)
                when (isLast) {
                    true -> {
                        path += resources.getString(R.string.path_tv_next)
                        path += resources.getString(R.string.path_tv_get_off, subPath.endName)
                    }
                    false -> {
                        // do nothing
                    }
                }
                path
            }
            false -> {
                var path = resources.getString(R.string.path_tv_transfer, subPath.startName)
                when (isLast) {
                    true -> {
                        path += resources.getString(R.string.path_tv_next)
                        path += resources.getString(R.string.path_tv_get_off, subPath.endName)
                    }
                    false -> {
                        // do nothing
                    }
                }
                path
            }
        }
    }
}