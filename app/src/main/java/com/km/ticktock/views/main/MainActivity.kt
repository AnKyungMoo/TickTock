package com.km.ticktock.views.main

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivityMainBinding
import com.km.ticktock.views.main.adapter.AppointmentAdapter
import com.km.ticktock.views.main.data.MainRepositoryImpl
import com.km.ticktock.views.main.domain.MainViewModel

class MainActivity : BaseActivity() {
    override val layoutRes = R.layout.activity_main
    override val isUseDatabinding: Boolean = true
    private val viewModel: MainViewModel = MainViewModel(MainRepositoryImpl())
    private lateinit var binding: ActivityMainBinding

    override fun setupViews() {
        initRecyclerViewPath()
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    private fun initRecyclerViewPath() {

        val layoutManager = LinearLayoutManager(this)
        binding.rcvAppointment.layoutManager = layoutManager
        val decoration = DividerItemDecoration(
            binding.rcvAppointment.context,
            layoutManager.orientation
        )
        binding.rcvAppointment.addItemDecoration(decoration)
        binding.rcvAppointment.adapter = AppointmentAdapter(this)
    }
}
