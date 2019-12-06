package com.km.ticktock.views.alarmsetting.adapter

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.km.ticktock.R
import com.km.ticktock.databinding.ItemAddAlarmBinding
import com.km.ticktock.databinding.ItemEditAlarmBinding
import com.km.ticktock.databinding.ItemNormalAlarmBinding
import com.km.ticktock.views.alarmsetting.entity.AlarmType
import com.km.ticktock.views.alarmsetting.model.AlarmItemModel

class AlarmAdapter: RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {
    private val alarmList = arrayListOf(
        AlarmItemModel("good", 5, AlarmType.NORMAL),
        AlarmItemModel("", 0, AlarmType.PLUS)
    )

    fun revertAlarmMode() {
        // 마지막 index는 + 버튼
        for (i in 0 until alarmList.size - 1) {
            when(alarmList[i].viewType) {
                AlarmType.NORMAL -> {
                    alarmList[i].viewType = AlarmType.EDIT
                    /* TODO: +버튼 가리자 */
                }
                AlarmType.EDIT -> {
                    alarmList[i].viewType = AlarmType.NORMAL
                }
            }
        }

        notifyDataSetChanged()
    }

    fun addAlarm(alarm: AlarmItemModel, index: Int) {
        alarmList.add(index, alarm)
        notifyItemInserted(index)
    }

    override fun getItemViewType(position: Int) = alarmList[position].viewType

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlarmViewHolder {
        val layoutInflater = viewGroup.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return when(viewType) {
            AlarmType.NORMAL -> {
                val bindingNormal: ItemNormalAlarmBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_normal_alarm, viewGroup, false)

                AlarmNormalViewHolder(bindingNormal)
            }
            AlarmType.EDIT -> {
                val bindingEdit: ItemEditAlarmBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_edit_alarm, viewGroup, false)

                AlarmEditViewHolder(bindingEdit)
            }
            else -> {
                val bindingPlus: ItemAddAlarmBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_add_alarm, viewGroup, false)

                AlarmPlusViewHolder(bindingPlus)
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

    inner class AlarmNormalViewHolder(binding: ItemNormalAlarmBinding) : AlarmViewHolder(binding) {
        override fun bind(item: AlarmItemModel) {
            (binding as ItemNormalAlarmBinding).editTitleThird.text = Editable.Factory.getInstance().newEditable(item.title)
            binding.editAlarmTime.text = Editable.Factory.getInstance().newEditable(item.time.toString())
            binding.root.setOnLongClickListener {
                revertAlarmMode()

                return@setOnLongClickListener false
            }
        }
    }

    inner class AlarmEditViewHolder(binding: ItemEditAlarmBinding) : AlarmViewHolder(binding) {
        override fun bind(item: AlarmItemModel) {
            (binding as ItemEditAlarmBinding).txtTitleThird.text = Editable.Factory.getInstance().newEditable(item.title)
            binding.txtAlarmTime.text = Editable.Factory.getInstance().newEditable(item.time.toString())
        }
    }

    inner class AlarmPlusViewHolder(binding: ItemAddAlarmBinding) : AlarmViewHolder(binding) {
        override fun bind(item: AlarmItemModel) {
            (binding as ItemAddAlarmBinding).imgPlusAddAlarm.setOnClickListener {
                addAlarm(AlarmItemModel("", 0, AlarmType.NORMAL), adapterPosition)
            }
        }
    }
}