package com.km.ticktock.views.alarmsetting

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivityLocationBinding
import com.km.ticktock.views.alarmsetting.adapter.PathAdapter
import com.km.ticktock.views.alarmsetting.data.LocationRepositoryImpl
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : BaseActivity() {
    override val layoutRes = R.layout.activity_location
    override val isUseDatabinding: Boolean = true
    private val viewModel: LocationViewModel = LocationViewModel(LocationRepositoryImpl())

    private lateinit var binding: ActivityLocationBinding

    override fun setupViews() {
        btn_x_location.setOnClickListener {
            finish()
        }

        edit_search_location.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.getKeywordSearch(edit_search_location.text.toString())
            }
        })

        initRecyclerViewPath()
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.vm = viewModel
    }

    private fun initRecyclerViewPath() {

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerviewMainList.layoutManager = layoutManager
        val decoration = DividerItemDecoration(
            binding.recyclerviewMainList.context,
            layoutManager.orientation
        )
        binding.recyclerviewMainList.addItemDecoration(decoration)
        binding.recyclerviewMainList.adapter = PathAdapter(this)
    }
}