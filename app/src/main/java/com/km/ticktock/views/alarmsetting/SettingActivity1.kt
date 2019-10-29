package com.km.ticktock.views.alarmsetting

import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivitySetting1Binding
import com.km.ticktock.utils.BasicUtils
import com.km.ticktock.views.alarmsetting.data.SettingRepositoryImpl1
import java.util.*

class SettingActivity1 : BaseActivity() {
    override val layoutRes: Int = R.layout.activity_setting_1
    override val isUseDatabinding: Boolean = true

    private lateinit var binding: ActivitySetting1Binding
    private val viewModel = SettingViewModel1(SettingRepositoryImpl1())
    private val pq = PriorityQueue<Int>()

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    override fun setupViews() {

        /* 에디트 텍스트 동작 이벤트
         */
        binding.edHour.apply {
            setOnEditorActionListener { _, actionId, _ -> when(actionId) {
                EditorInfo.IME_ACTION_NEXT -> {
                    viewModel.requestHourText(getHour())
                    binding.edMinute.requestFocus()
                    true
                }
                else -> false
            } }
            setOnFocusChangeListener { _, hasFocus -> when(hasFocus) {
                true -> setTimeActive(active = true)
            } }
        }

        binding.edMinute.apply {
            setOnEditorActionListener { v, actionId, _ -> when(actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    viewModel.requestMinuteText(getMinute())
                    onCloseKeyboard(v)
                    true
                }
                else -> false
            } }
            setOnFocusChangeListener { _, hasFocus -> when(hasFocus) {
                true -> setTimeActive(active = true)
                false -> setTimeActive(active = false)
            } }
        }

        when (BasicUtils.getAMPM()) {
            Calendar.AM -> setTime(isPM = false)
            Calendar.PM -> setTime(isPM = true)
        }
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

    private fun setTimeActive(active: Boolean) {

        binding.ivHourUp.isSelected = active
        binding.edHour.isSelected = active
        binding.ivHourDown.isSelected = active
        binding.tvTimeDivide.isSelected = active
        binding.ivMinuteUp.isSelected = active
        binding.edMinute.isSelected = active
        binding.ivMinuteDown.isSelected = active
    }

    /* 클릭 이벤트 바인딩
     */
    fun onBackClick(v: View) {
        finish()
    }

    /* 요일
     */
    private fun onDayClick(v: View, day: Int) {
        when (v.isSelected) {
            true -> pq.add(day)
            false -> pq.remove(day)
        }
        viewModel.requestNextStepClickable(pq)
    }

    fun onMondayClick(v: View) {
        setTimeActive(active = false)
        v.isSelected = !v.isSelected
        onDayClick(v, 1)
    }

    fun onTuesdayClick(v: View) {
        setTimeActive(active = false)
        v.isSelected = !v.isSelected
        onDayClick(v, 2)
    }

    fun onWednesdayClick(v: View) {
        setTimeActive(active = false)
        v.isSelected = !v.isSelected
        onDayClick(v, 3)
    }

    fun onThursdayClick(v: View) {
        setTimeActive(active = false)
        v.isSelected = !v.isSelected
        onDayClick(v, 4)
    }

    fun onFridayClick(v: View) {
        setTimeActive(active = false)
        v.isSelected = !v.isSelected
        onDayClick(v, 5)
    }

    fun onSaturdayClick(v: View) {
        setTimeActive(active = false)
        v.isSelected = !v.isSelected
        onDayClick(v, 6)
    }

    fun onSundayClick(v: View) {
        setTimeActive(active = false)
        v.isSelected = !v.isSelected
        onDayClick(v, 7)
    }

    /* 시간
     */
    fun onHourUpClick(v: View) {
        setTimeActive(true)
        viewModel.requestHourText(getHour()-1)
    }

    fun onHourDownClick(v: View) {
        setTimeActive(true)
        viewModel.requestHourText(getHour()+1)
    }

    fun onMinuteUpClick(v: View) {
        setTimeActive(true)
        viewModel.requestMinuteText(getMinute()-1)
    }

    fun onMinuteDownClick(v: View) {
        setTimeActive(true)
        if (getMinute() == 59) {
            viewModel.requestHourText(getHour()+1)
        }
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
        setTimeActive(active = false)
        this.currentFocus?.let { view ->
            when (view) {
                binding.edHour -> viewModel.requestHourText(getHour())
                binding.edMinute -> viewModel.requestMinuteText(getMinute())
            }
            view.clearFocus()
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
