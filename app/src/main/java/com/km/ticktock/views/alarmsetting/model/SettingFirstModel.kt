package com.km.ticktock.views.alarmsetting.model

import android.os.Parcel
import android.os.Parcelable
import com.km.ticktock.views.alarmsetting.entity.DayType
import com.km.ticktock.views.alarmsetting.entity.TimeType

data class SettingFirstModel(
    val dayList: List<DayType>,
    val hour: Int,
    val minute: Int,
    val timeType: TimeType // AM or PM
) : Parcelable {
    constructor(source: Parcel) : this(
        ArrayList<DayType>().apply { source.readList(this as List<DayType>, DayType::class.java.classLoader) },
        source.readInt(),
        source.readInt(),
        TimeType.values()[source.readInt()]
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeList(dayList)
        writeInt(hour)
        writeInt(minute)
        writeInt(timeType.ordinal)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SettingFirstModel> =
            object : Parcelable.Creator<SettingFirstModel> {
                override fun createFromParcel(source: Parcel): SettingFirstModel =
                    SettingFirstModel(source)

                override fun newArray(size: Int): Array<SettingFirstModel?> = arrayOfNulls(size)
            }
    }
}