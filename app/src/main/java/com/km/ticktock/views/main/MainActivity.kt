package com.km.ticktock.views.main

import androidx.databinding.DataBindingUtil
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivityMainBinding
import com.km.ticktock.views.main.data.MainRepositoryImpl
import com.km.ticktock.views.main.domain.MainViewModel

class MainActivity : BaseActivity() {
    override val layoutRes = R.layout.activity_main
    override val isUseDatabinding: Boolean = true
    private val viewModel: MainViewModel = MainViewModel(MainRepositoryImpl())
    private lateinit var binding: ActivityMainBinding

    override fun setupViews() {
        viewModel.funfun(this)
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }
}
