package com.km.ticktock.views.alarmsetting

import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivitySetting2Binding
import kotlinx.android.synthetic.main.activity_setting_2.*

class SettingActivity2 : BaseActivity() {
    override val layoutRes: Int = R.layout.activity_setting_2
    override val isUseDatabinding: Boolean = true

    private lateinit var binding: ActivitySetting2Binding

    override fun setupViews() {
        layout_source_setting2.setOnClickListener {
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }
}