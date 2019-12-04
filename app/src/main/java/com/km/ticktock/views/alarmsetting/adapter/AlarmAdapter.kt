package com.km.ticktock.views.alarmsetting.adapter

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.km.ticktock.R
import com.km.ticktock.databinding.ItemEditAlarmBinding
import com.km.ticktock.databinding.ItemNormalAlarmBinding
import com.km.ticktock.views.alarmsetting.entity.AlarmType
import com.km.ticktock.views.alarmsetting.model.AlarmItemModel

class AlarmAdapter: RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {
    private val alarmList = arrayListOf<AlarmItemModel>(
        AlarmItemModel("good", 5, AlarmType.NORMAL),
        AlarmItemModel("time", 10, AlarmType.EDIT),
        AlarmItemModel("time", 10, AlarmType.NORMAL),
        AlarmItemModel("time", 10, AlarmType.EDIT),
        AlarmItemModel("time", 10, AlarmType.NORMAL),
        AlarmItemModel("time", 10, AlarmType.EDIT),
        AlarmItemModel("time", 10, AlarmType.NORMAL)
    )

    fun addAlarm(alarm: AlarmItemModel) = alarmList.add(alarm)

    override fun getItemViewType(position: Int) = alarmList[position].viewType

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlarmViewHolder {
        val layoutInflater = viewGroup.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return when(viewType) {
            AlarmType.NORMAL -> {
                val binding: ItemNormalAlarmBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_normal_alarm, viewGroup, false)

                AlarmNormalViewHolder(binding)
            }
            else -> {
                val binding2: ItemEditAlarmBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_edit_alarm, viewGroup, false)

                AlarmEditViewHolder(binding2)
            }
        }
    }

    override fun getItemCount() = alarmList.size

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(alarmList[position])
    }


    abstract class AlarmViewHolder(protected val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: AlarmItemModel)
    }

    class AlarmNormalViewHolder(binding: ItemNormalAlarmBinding) : AlarmViewHolder(binding) {
        override fun bind(item: AlarmItemModel) {
            (binding as ItemNormalAlarmBinding).editTitleThird.text = Editable.Factory.getInstance().newEditable(item.title)
            binding.editAlarmTime.text = Editable.Factory.getInstance().newEditable(item.time.toString())
        }
    }

    class AlarmEditViewHolder(binding: ItemEditAlarmBinding) : AlarmViewHolder(binding) {
        override fun bind(item: AlarmItemModel) {
            (binding as ItemEditAlarmBinding).txtTitleThird.text = Editable.Factory.getInstance().newEditable(item.title)
            binding.txtAlarmTime.text = Editable.Factory.getInstance().newEditable(item.time.toString())
        }
    }
}