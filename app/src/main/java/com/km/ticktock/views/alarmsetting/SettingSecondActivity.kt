package com.km.ticktock.views.alarmsetting

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivitySettingSecondBinding

class SettingSecondActivity : BaseActivity() {
    override val layoutRes: Int = R.layout.activity_setting_second
    override val isUseDatabinding: Boolean = true

    private lateinit var binding: ActivitySettingSecondBinding

    override fun setupViews() {
        searchClick()
    }

    private fun searchClick() {
        val owner = this

        with(binding.vm as SettingSecondViewModel) {
            sourceClickEvent.observe(owner, Observer {
                val intent = Intent(owner, SearchActivity::class.java)
                intent.putExtra("focus", 1)
                startActivity(intent)
            })

            destinationClickEvent.observe(owner, Observer {
                val intent = Intent(owner, SearchActivity::class.java)
                intent.putExtra("focus", 2)
                startActivity(intent)
            })
        }
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.vm = SettingSecondViewModel()
    }
}