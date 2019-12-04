package com.km.ticktock.views.alarmsetting.adapter

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.ticktock.R
import com.km.ticktock.databinding.ItemAlarmBinding
import com.km.ticktock.views.alarmsetting.model.AlarmItemModel

class AlarmAdapter: RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {
    private val list = arrayListOf<AlarmItemModel>(
        AlarmItemModel("good", 5),
        AlarmItemModel("time", 10),
        AlarmItemModel("time", 10),
        AlarmItemModel("time", 10),
        AlarmItemModel("time", 10),
        AlarmItemModel("time", 10),
        AlarmItemModel("time", 10)
    )
    lateinit var binding: ItemAlarmBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlarmViewHolder {
        val layoutInflater = viewGroup.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_alarm, viewGroup, false)

        return AlarmViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class AlarmViewHolder(private val binding: ItemAlarmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlarmItemModel) {
            binding.editTitleThird.text = Editable.Factory.getInstance().newEditable(item.title)
            binding.editAlarmTime.text = Editable.Factory.getInstance().newEditable(item.time.toString())
        }
    }
}