package com.km.ticktock.views.alarmsetting.adapter

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.MotionEvent
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
import java.util.*

class AlarmAdapter(val itemDragListener: ItemDragListener): RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {
    private val alarmList = arrayListOf(
        AlarmItemModel("good", 5, AlarmType.NORMAL),
        AlarmItemModel("", 0, AlarmType.PLUS)
    )

    /* TODO: 더 깔끔하게 만들 수 있는지 고민해보자.. */
    fun revertAlarmMode() {
        // PLUS 버튼을 제외한 alarm 객체들 revert
        for (i in 0 until alarmList.size - 1) {
            when(alarmList[i].viewType) {
                AlarmType.NORMAL -> {
                    alarmList[i].viewType = AlarmType.EDIT
                }
                AlarmType.EDIT -> {
                    alarmList[i].viewType = AlarmType.NORMAL
                }
            }
        }

        // 마지막 PLUS 버튼 처리
        val index = alarmList.size - 1
        when(alarmList[index].viewType) {
            AlarmType.PLUS -> {
                alarmList.removeAt(index)
            }
            else -> {
                alarmList[index].viewType = AlarmType.NORMAL
                alarmList.add(AlarmItemModel("", 0, AlarmType.PLUS))
            }
        }

        notifyDataSetChanged()
    }

    fun addAlarm(alarm: AlarmItemModel, index: Int) {
        alarmList.add(index, alarm)
        notifyItemInserted(index)
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(alarmList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
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

            /* TODO: 나중에 완료 버튼 생기면 바꾸자 */
            binding.root.setOnLongClickListener {
                revertAlarmMode()

                return@setOnLongClickListener false
            }

            binding.imgMoveAlarm.setOnTouchListener{ _, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    itemDragListener.onStartDrag(this)
                }

                return@setOnTouchListener false
            }
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