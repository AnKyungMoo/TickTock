package com.km.ticktock.views.alarmsetting

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivitySearchBinding
import com.km.ticktock.views.alarmsetting.adapter.SearchAdapter
import com.km.ticktock.views.alarmsetting.entity.LocationItemDecoration


class SearchActivity : BaseActivity() {
    override val layoutRes: Int = R.layout.activity_search
    override val isUseDatabinding: Boolean = true

    lateinit var binding: ActivitySearchBinding

    override fun setupViews() {
        initFocus()
        searchLocation()
        initRecyclerViewPath()
    }

    private fun initFocus() {
        when (intent.getIntExtra("focus", 1)) {
            1 -> binding.editSearchSource.requestFocus()
            2 -> binding.editSearchDestination.requestFocus()
        }
    }

    private fun searchLocation() {
        val owner = this
        with((binding.vm as SearchViewModel)) {
            searchText.observe(owner, Observer {
                getKeywordSearch(it)
            })
        }
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.vm = SearchViewModel()
        binding.lifecycleOwner = this
    }

    private fun initRecyclerViewPath() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerviewSearchList.layoutManager = layoutManager
        binding.recyclerviewSearchList.addItemDecoration(LocationItemDecoration(
            applicationContext.resources.getDrawable(R.drawable.location_space_divider, theme)
        ))
        binding.recyclerviewSearchList.adapter = SearchAdapter(this)
        binding.recyclerviewSearchList.setHasFixedSize(true)
    }
}