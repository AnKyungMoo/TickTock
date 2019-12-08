package com.km.ticktock.views.alarmsetting

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivityThirdSettingBinding
import com.km.ticktock.views.alarmsetting.adapter.ItemAlarmDecoration
import com.km.ticktock.views.alarmsetting.adapter.AlarmAdapter
import com.km.ticktock.views.alarmsetting.adapter.ItemAlarmTouchHelperCallback
import com.km.ticktock.views.alarmsetting.adapter.ItemDragListener

class SettingThirdActivity : BaseActivity(), ItemDragListener {
    override val layoutRes: Int = R.layout.activity_third_setting
    override val isUseDatabinding: Boolean = true

    private lateinit var binding: ActivityThirdSettingBinding
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onStartDrag(viewHolder: AlarmAdapter.AlarmEditViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun setupViews() {
        initRecyclerView()
    }

    override fun onDataBinding() {
        super.onDataBinding()
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    private fun initRecyclerView() {
        binding.recyclerviewAlarmList.layoutManager = LinearLayoutManager(this)

        val adapter = AlarmAdapter(this)

        itemTouchHelper = ItemTouchHelper(ItemAlarmTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerviewAlarmList)

        binding.recyclerviewAlarmList.adapter = adapter
        binding.recyclerviewAlarmList.addItemDecoration(ItemAlarmDecoration(this))
        binding.recyclerviewAlarmList.setHasFixedSize(true)
    }
}