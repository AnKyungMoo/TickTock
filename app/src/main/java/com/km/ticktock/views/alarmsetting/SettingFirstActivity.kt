package com.km.ticktock.views.alarmsetting

import android.content.Intent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivitySettingFirstBinding
import com.km.ticktock.utils.BasicUtils
import com.km.ticktock.views.alarmsetting.entity.DayType
import com.km.ticktock.views.alarmsetting.entity.TimeType
import com.km.ticktock.views.alarmsetting.model.SettingFirstModel
import java.util.*
import kotlin.collections.ArrayList

class SettingFirstActivity : BaseActivity() {
    override val layoutRes: Int = R.layout.activity_setting_first
    override val isUseDatabinding: Boolean = true

    private lateinit var binding: ActivitySettingFirstBinding
    private val viewModel = SettingFirstViewModel()
    private val pq = PriorityQueue<Int>() // 선택된 요일을 순서대로 보유하기 위한 우선순위 큐

    companion object {
        const val EXTRA_SETTING_FIRST = "EXTRA_SETTING_FIRST"
    }

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
                    /* Hour 선택을 완료한 경우
                     * 1. 올바른 Hour 범위(0 ~ 12)로 적용
                     * 2. Minute 로 포커싱
                     */
                    viewModel.requestHourText()
                    binding.edMinute.requestFocus()
                    true
                }
                else -> false
            } }
            setOnFocusChangeListener { _, hasFocus -> when(hasFocus) {
                true -> setTimeActive(true)
            } }
        }

        binding.edMinute.apply {
            setOnEditorActionListener { _, actionId, _ -> when(actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    /* Minute 선택을 완료한 경우
                     * 1. 올바른 Minute 범위(0 ~ 59)로 적용
                     * 2. 키보드 내림
                     */
                    viewModel.requestMinuteText()
                    viewModel.onCloseKeyboard()
                    true
                }
                else -> false
            } }
            setOnFocusChangeListener { _, hasFocus -> when(hasFocus) {
                true -> setTimeActive(true)
                false -> setTimeActive(false)
            } }
        }

        /* 현재 시간이 AM or PM 일때 분기
         */
        when (BasicUtils.getAMPM()) {
            Calendar.AM -> setMeridiem(isPM = false)
            Calendar.PM -> setMeridiem(isPM = true)
        }

        /* 클릭 이벤트 & 데이터 변경 여부를
         * 뷰모델로 관찰
         */
        observeSettingFirstViewModel()
    }

    /* 요일 선택 여부에 따라 우선순위 큐에 insert
     * 비어있지 않다면 다음 단계 버튼 활성화
     */
    private fun onDayClick(isSelected: Boolean, dayType: DayType) {
        when (isSelected) {
            true -> pq.add(dayType.value)
            false -> pq.remove(dayType.value)
        }
        viewModel.requestNextStepClickable(pq)
    }

    /* 시간 변경 시 관련된 뷰 활성화
     */
    private fun setTimeActive(active: Boolean) {

        binding.ivHourUp.isSelected = active
        binding.edHour.isSelected = active
        binding.ivHourDown.isSelected = active
        binding.tvTimeDivide.isSelected = active
        binding.ivMinuteUp.isSelected = active
        binding.edMinute.isSelected = active
        binding.ivMinuteDown.isSelected = active
    }

    /* AM or PM 일때 버튼 적용
     */
    private fun setMeridiem(isPM: Boolean) {

        if (isPM) {
            binding.btnPm.isSelected = true
            binding.btnAm.isSelected = false
        } else {
            binding.btnPm.isSelected = false
            binding.btnAm.isSelected = true
        }
    }

    /* SettingSecondActivity 로 전달하기 위한 객체화
     */
    private fun getSettingFirstModel(): SettingFirstModel {

        val dayList = ArrayList<DayType>()
        for (day in pq) {
            val dayType = when (day) {
                1 -> DayType.MONDAY
                2 -> DayType.TUESDAY
                3 -> DayType.WEDNESDAY
                4 -> DayType.THURSDAY
                5 -> DayType.FRIDAY
                6 -> DayType.SATURDAY
                7 -> DayType.SUNDAY
                else -> DayType.MONDAY
            }
            dayList.add(dayType)
        }
        val hour = viewModel.hourText.value!!.toInt()
        val minute = viewModel.minuteText.value!!.toInt()
        val timeType: TimeType = when (binding.btnPm.isSelected) {
            true -> TimeType.PM
            false -> TimeType.AM
        }
        return SettingFirstModel(dayList, hour, minute, timeType)
    }

    /* 클릭 이벤트 바인딩
     */
    private fun observeSettingFirstViewModel() {

        val owner = this
        with(viewModel) {
            /* 뒤로가기
             */
            backClickEvent.observe(owner, Observer {
                finish()
            })

            /* 요일
             */
            dayClickEvent.observe(owner, Observer { dayType ->
                setTimeActive(false)
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
                    else -> {}
                }
            })

            /* 시간
             */
            timeClickEvent.observe(owner, Observer { timeType ->
                setTimeActive(true)
                when (timeType) {
                    TimeType.PM -> setMeridiem(isPM = true)
                    TimeType.AM -> setMeridiem(isPM = false)
                    else -> {
                        // do nothing
                    }
                }
            })

            /* SettingSecondActivity 로 이동
             */
            nextClickEvent.observe(owner, Observer {
                val intent = Intent(owner, SettingActivity2::class.java)
                intent.putExtra(EXTRA_SETTING_FIRST, getSettingFirstModel())
                startActivity(intent)
            })

            /* 키보드 내리기 & 포커스 해제
             */
            closeKeyboardEvent.observe(owner, Observer {
                setTimeActive(false)
                owner.currentFocus?.let { view ->
                    when (view) {
                        binding.edHour -> viewModel.requestHourText()
                        binding.edMinute -> viewModel.requestMinuteText()
                    }
                    view.clearFocus()
                    val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                }
            })
        }
    }
}
