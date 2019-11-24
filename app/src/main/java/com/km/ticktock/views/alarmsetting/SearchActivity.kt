package com.km.ticktock.views.alarmsetting

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.km.ticktock.R
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivitySearchBinding
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {
    override val layoutRes: Int = R.layout.activity_search
    override val isUseDatabinding: Boolean = true

    lateinit var binding: ActivitySearchBinding

    override fun setupViews() {
        searchLocation()
    }

    private fun searchLocation() {
        /* TODO: LiveData를 이용해서 할 수 있는 방법이 있지 않을까.. */
        edit_search_location.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("호출 됩니까..", p0.toString())
                (binding.vm as SearchViewModel).getKeywordSearch(p0.toString())
            }

        })
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.vm = SearchViewModel()
        binding.lifecycleOwner = this
    }
}