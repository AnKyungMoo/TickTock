package com.km.ticktock.views.alarmsetting.adapter

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemAlarmDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.bottom = dpTopx(context, 10f)
        outRect.left = dpTopx(context, 30f)
        outRect.right = dpTopx(context, 30f)
    }

    fun dpTopx(context: Context, dp: Float) : Int {
        return TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }
}