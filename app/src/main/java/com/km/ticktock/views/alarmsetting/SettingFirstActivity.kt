package com.km.ticktock.views.alarmsetting

import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivitySettingFirstBinding
import com.km.ticktock.views.alarmsetting.data.SettingFirstRepositoryImpl

class SettingFirstActivity : BaseActivity() {
    override val layoutRes: Int = R.layout.activity_setting_first
    override val isUseDatabinding: Boolean = true

    private lateinit var binding: ActivitySettingFirstBinding
    private val viewModel = SettingFirstViewModel(SettingFirstRepositoryImpl())

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    override fun setupViews() {

        /* 에디트 텍스트 동작 이벤트
         */
        binding.edHour.setOnEditorActionListener { _, actionId, _ -> when(actionId) {
            EditorInfo.IME_ACTION_NEXT -> {
                viewModel.requestHourText(getHour())
                binding.edMinute.requestFocus()
                true
            }
            else -> false
        } }

        binding.edMinute.setOnEditorActionListener { v, actionId, _ -> when(actionId) {
            EditorInfo.IME_ACTION_DONE -> {
                viewModel.requestMinuteText(getMinute())
                onCloseKeyboard(v)
                true
            }
            else -> false
        } }

        setTime(isPM = true)
    }

    private fun getHour(): Int {
        var hour = 0
        val hourString = binding.edHour.text.toString()
        if (hourString.compareTo("") != 0) {
            hour = hourString.toInt()
        }
        return hour
    }

    private fun getMinute(): Int {
        var minute = 0
        val minuteString = binding.edMinute.text.toString()
        if (minuteString.compareTo("") != 0) {
            minute = minuteString.toInt()
        }
        return minute
    }

    private fun setTime(isPM: Boolean) {

        if (isPM) {
            binding.btnPm.isSelected = true
            binding.btnAm.isSelected = false
        } else {
            binding.btnPm.isSelected = false
            binding.btnAm.isSelected = true
        }
    }

    /* 클릭 이벤트 바인딩
     */
    fun onBackClick(v: View) {
        finish()
    }

    /* 요일
     */
    fun onMondayClick(v: View) {
        v.isSelected = !v.isSelected
    }
    fun onTuesdayClick(v: View) {
        v.isSelected = !v.isSelected
    }
    fun onWednesdayClick(v: View) {
        v.isSelected = !v.isSelected
    }
    fun onThursdayClick(v: View) {
        v.isSelected = !v.isSelected
    }
    fun onFridayClick(v: View) {
        v.isSelected = !v.isSelected
    }
    fun onSaturdayClick(v: View) {
        v.isSelected = !v.isSelected
    }
    fun onSundayClick(v: View) {
        v.isSelected = !v.isSelected
    }

    /* 시간
     */
    fun onHourUpClick(v: View) {
        viewModel.requestHourText(getHour()-1)
    }
    fun onHourDownClick(v: View) {
        viewModel.requestHourText(getHour()+1)
    }
    fun onMinuteUpClick(v: View) {
        viewModel.requestMinuteText(getMinute()-1)
    }
    fun onMinuteDownClick(v: View) {
        viewModel.requestMinuteText(getMinute()+1)
    }
    fun onPMClick(v: View) {
        setTime(isPM = true)
    }
    fun onAMClick(v: View) {
        setTime(isPM = false)
    }

    fun onNextStepClick(v: View) {

    }

    /* 키보드 내리기 & 포커스 해제
     */
    fun onCloseKeyboard(v: View) {
        this.currentFocus?.let { view ->
            view.clearFocus()
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
