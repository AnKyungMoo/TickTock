package com.km.ticktock.views.alarmsetting

import androidx.databinding.DataBindingUtil
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivityLocationBinding
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : BaseActivity() {
    override val layoutRes = R.layout.activity_location
    override val isUseDatabinding: Boolean = true

    private lateinit var binding: ActivityLocationBinding

    override fun setupViews() {
        btn_x_location.setOnClickListener {
            finish()
        }
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }
}