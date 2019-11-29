package com.km.ticktock.views.alarmsetting

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivitySearchBinding
import com.km.ticktock.services.RetrofitService
import com.km.ticktock.views.alarmsetting.adapter.SearchAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : BaseActivity() {
    override val layoutRes: Int = R.layout.activity_search
    override val isUseDatabinding: Boolean = true

    lateinit var subscription: Disposable
    lateinit var binding: ActivitySearchBinding

    override fun setupViews() {
        searchLocation()
        initRecyclerViewPath()
    }

    private fun searchLocation() {
        /* TODO: LiveData를 이용해서 할 수 있는 방법이 있지 않을까.. */
        edit_search_source.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                getKeywordSearch(p0.toString())
            }
        })
    }

    /* TODO: 이건 어디에 있어야하지..? */
    fun getKeywordSearch(keyword: String) {
            subscription = RetrofitService.restAPI().keywordSearch(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        (binding.recyclerviewSearchList.adapter as SearchAdapter)
                            .addLocations(result.documents)
                    },
                    { err ->
                        Log.e("Error User", err.toString())
                    }
                )
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.vm = SearchViewModel()
        binding.lifecycleOwner = this
    }

    private fun initRecyclerViewPath() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerviewSearchList.layoutManager = layoutManager
        binding.recyclerviewSearchList.adapter = SearchAdapter(this)
        binding.recyclerviewSearchList.setHasFixedSize(true)
    }
}