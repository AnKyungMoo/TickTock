package com.km.ticktock.views.location

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.ticktock.base.BaseActivity
import com.km.ticktock.databinding.ActivityLocationBinding
import com.km.ticktock.views.location.adapter.PathAdapter
import com.km.ticktock.views.location.entity.SearchPubTransPath
import androidx.recyclerview.widget.DividerItemDecoration



class LocationActivity : BaseActivity() {
    override val layoutRes = com.km.ticktock.R.layout.activity_location
    override val isUseDatabinding: Boolean = true

    private lateinit var binding: ActivityLocationBinding

    override fun setupViews() {

        val layoutManager = LinearLayoutManager(this)
        binding.rcvPath.layoutManager = layoutManager
        val decoration = DividerItemDecoration(
            binding.rcvPath.context,
            layoutManager.orientation
        )
        binding.rcvPath.addItemDecoration(decoration)
        binding.rcvPath.adapter = PathAdapter(this, getTransPaths())
    }

    override fun onDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    private fun getTransPaths(): ArrayList<SearchPubTransPath> {

        val transPaths = ArrayList<SearchPubTransPath>()

        val subPath1 = ArrayList<SearchPubTransPath.SubPath>()
        subPath1.add(SearchPubTransPath.SubPath(
            3,5,
            SearchPubTransPath.Lane("", "", 0, 0),
            "", ""
        ))
        subPath1.add(SearchPubTransPath.SubPath(
            1,9,
            SearchPubTransPath.Lane("", "", 0, 2),
            "합정역", "시청역"
        ))
        subPath1.add(SearchPubTransPath.SubPath(
            3,5,
            SearchPubTransPath.Lane("", "", 0, 0),
            "", ""
        ))
        subPath1.add(SearchPubTransPath.SubPath(
            1,10,
            SearchPubTransPath.Lane("", "", 0, 1),
            "시청역", "종각역"
        ))
        subPath1.add(SearchPubTransPath.SubPath(
            3,5,
            SearchPubTransPath.Lane("", "", 0, 0),
            "", ""
        ))

        transPaths.add(SearchPubTransPath(1, SearchPubTransPath.Path(
            10, 34, 6250,
            0, 0, 0),
            subPath1)
        )

        val subPath2 = ArrayList<SearchPubTransPath.SubPath>()
        subPath2.add(SearchPubTransPath.SubPath(
            3,5,
            SearchPubTransPath.Lane("", "", 0, 0),
            "", ""
        ))
        subPath2.add(SearchPubTransPath.SubPath(
            2,21,
            SearchPubTransPath.Lane("", "740", 3, 0),
            "전쟁기념관", "종각역 3번 출구"
        ))
        subPath2.add(SearchPubTransPath.SubPath(
            3,5,
            SearchPubTransPath.Lane("", "", 0, 0),
            "", ""
        ))

        transPaths.add(SearchPubTransPath(1, SearchPubTransPath.Path(
            15, 31, 6250,
            0, 0, 0),
            subPath2)
        )
        return transPaths
    }
}