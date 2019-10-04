package com.km.ticktock.views.alarmsetting.adapter

import android.content.Context
import android.content.res.Resources
import com.km.ticktock.R
import com.km.ticktock.views.alarmsetting.entity.SearchPubTransPath

class SubPathBindingAdapter {

    companion object {

        private lateinit var resources: Resources

        fun init(context: Context) {
            resources = context.resources
        }

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