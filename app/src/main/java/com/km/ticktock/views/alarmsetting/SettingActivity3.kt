package com.km.ticktock.views.alarmsetting

import androidx.databinding.DataBindingUtil
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivitySetting3Binding

class SettingActivity3 : BaseActivity() {
    override val layoutRes: Int = R.layout.activity_setting_3
    override val isUseDatabinding: Boolean = true

    private lateinit var binding: ActivitySetting3Binding

    override fun setupViews() {

    }

    override fun onDataBinding() {
        super.onDataBinding()
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }
}