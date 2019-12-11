package com.km.ticktock.views.main

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivityMainBinding
import com.km.ticktock.views.alarmsetting.entity.DayType
import com.km.ticktock.views.main.adapter.AppointmentAdapter

class MainActivity : BaseActivity() {
    override val layoutRes = R.layout.activity_main
    override val isUseDatabinding: Boolean = true
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun setupViews() {
        initRecyclerViewPath()
        observeMainViewModel()
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun initRecyclerViewPath() {

        val context = this
        binding.rcvAppointment.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AppointmentAdapter()
        }
    }

    /* 요일 선택 여부에 따라 우선순위 큐에 insert
     * 비어있지 않다면 다음 단계 버튼 활성화
     */
    private fun onDayClick(isSelected: Boolean, dayType: DayType) {

    }

    /* 클릭 이벤트 바인딩
    */
    private fun observeMainViewModel() {

        val owner = this
        with(viewModel) {
            /* 요일
             */
            dayClickEvent.observe(owner, Observer { dayType ->
                when (dayType) {
                    DayType.MONDAY -> {
                        binding.btnMonday.apply { isSelected = !isSelected }
                        onDayClick(binding.btnMonday.isSelected, dayType)
                    }
                    DayType.TUESDAY -> {
                        binding.btnTuesday.apply { isSelected = !isSelected }
                        onDayClick(binding.btnTuesday.isSelected, dayType)
                    }
                    DayType.WEDNESDAY -> {
                        binding.btnWednesday.apply { isSelected = !isSelected }
                        onDayClick(binding.btnWednesday.isSelected, dayType)
                    }
                    DayType.THURSDAY -> {
                        binding.btnThursday.apply { isSelected = !isSelected }
                        onDayClick(binding.btnThursday.isSelected, dayType)
                    }
                    DayType.FRIDAY -> {
                        binding.btnFriday.apply { isSelected = !isSelected }
                        onDayClick(binding.btnFriday.isSelected, dayType)
                    }
                    DayType.SATURDAY -> {
                        binding.btnSaturday.apply { isSelected = !isSelected }
                        onDayClick(binding.btnSaturday.isSelected, dayType)
                    }
                    DayType.SUNDAY -> {
                        binding.btnSunday.apply { isSelected = !isSelected }
                        onDayClick(binding.btnSunday.isSelected, dayType)
                    }
                    else -> {
                    }
                }
            })
        }
    }
}
