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
        /* TODO: focus 차이를 둘 것 */
        with(binding.vm as SettingSecondViewModel) {
            sourceClickEvent.observe(owner, Observer {
                val intent = Intent(owner, SearchActivity::class.java)
                startActivity(intent)
            })

            /* TODO: LocationActivity -> SearchActivity 로 바꾸자 */
            destinationClickEvent.observe(owner, Observer {
                val intent = Intent(owner, LocationActivity::class.java)
                startActivity(intent)
            })
        }
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.vm = SettingSecondViewModel()
    }
}