package com.km.ticktock.views.alarmsetting.adapter

import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.km.ticktock.R
import com.km.ticktock.databinding.ItemPathBinding
import com.km.ticktock.utils.BasicUtils
import com.km.ticktock.views.location.entity.SearchPubTransPath

class PathAdapter(val context: Context?, var transPaths: ArrayList<SearchPubTransPath>): RecyclerView.Adapter<PathAdapter.PathViewHolder>() {

    private lateinit var binding: ItemPathBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PathViewHolder {
        val layoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_path, viewGroup, false)

        return PathViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PathViewHolder, position: Int) {

        holder.bind(transPaths[position].path)
        setBarPath(transPaths[position])
        setSubPath(transPaths[position])
    }

    private fun setBarPath(transPath: SearchPubTransPath) {

        val totalLength = BasicUtils.getWidth() - 80 * transPath.subPathList.size
        val partOfLength = totalLength.toDouble() / transPath.path.totalTime.toDouble()

        for (j in transPath.subPathList.indices) {
            val sectionTime = View(context)
            var width = partOfLength * transPath.subPathList[j].sectionTime
            width -= width / 10.0
            sectionTime.layoutParams = LinearLayout.LayoutParams(80 + width.toInt(), LinearLayout.LayoutParams.MATCH_PARENT)

            when (transPath.subPathList[j].trafficType) {
                1 -> {
                    when (transPath.subPathList[j].lane.subwayCode) {
                        1 -> sectionTime.setBackgroundResource(R.color.subway_line1)
                        2 -> sectionTime.setBackgroundResource(R.color.subway_line2)
                        3 -> sectionTime.setBackgroundResource(R.color.subway_line3)
                        4 -> sectionTime.setBackgroundResource(R.color.subway_line4)
                        5 -> sectionTime.setBackgroundResource(R.color.subway_line5)
                        6 -> sectionTime.setBackgroundResource(R.color.subway_line6)
                        7 -> sectionTime.setBackgroundResource(R.color.subway_line7)
                        8 -> sectionTime.setBackgroundResource(R.color.subway_line8)
                        9 -> sectionTime.setBackgroundResource(R.color.subway_line9)
                        100 -> sectionTime.setBackgroundResource(R.color.subway_bundang)
                        101 -> sectionTime.setBackgroundResource(R.color.subway_airport)
                        102 -> sectionTime.setBackgroundResource(R.color.subway_jagi)
                        104 -> sectionTime.setBackgroundResource(R.color.subway_gyeongui)
                        107 -> sectionTime.setBackgroundResource(R.color.subway_everline)
                        108 -> sectionTime.setBackgroundResource(R.color.subway_gyeongchoon)
                        109 -> sectionTime.setBackgroundResource(R.color.subway_shinbundang)
                        110 -> sectionTime.setBackgroundResource(R.color.subway_uijeongbu)
                        111 -> sectionTime.setBackgroundResource(R.color.subway_suin)
                        112 -> sectionTime.setBackgroundResource(R.color.subway_geonggang)
                        113 -> sectionTime.setBackgroundResource(R.color.subway_wooee)
                        114 -> sectionTime.setBackgroundResource(R.color.subway_seohae)
                        21 -> sectionTime.setBackgroundResource(R.color.subway_incheon1)
                        22 -> sectionTime.setBackgroundResource(R.color.subway_incheon2)
                        31 -> sectionTime.setBackgroundResource(R.color.subway_daejeon1)
                        41 -> sectionTime.setBackgroundResource(R.color.subway_daegu1)
                        42 -> sectionTime.setBackgroundResource(R.color.subway_daegu2)
                        43 -> sectionTime.setBackgroundResource(R.color.subway_daegu3)
                        51 -> sectionTime.setBackgroundResource(R.color.subway_gwangju1)
                        71 -> sectionTime.setBackgroundResource(R.color.subway_busan1)
                        72 -> sectionTime.setBackgroundResource(R.color.subway_busan2)
                        73 -> sectionTime.setBackgroundResource(R.color.subway_busan3)
                        74 -> sectionTime.setBackgroundResource(R.color.subway_busan4)
                        78 -> sectionTime.setBackgroundResource(R.color.subway_east)
                        79 -> sectionTime.setBackgroundResource(R.color.subway_gimhae)
                        else -> sectionTime.setBackgroundResource(R.color.subway_line1)
                    }
                }
                2 -> {
                    when (transPath.subPathList[j].lane.type) {
                        1 -> sectionTime.setBackgroundResource(R.color.bus_normal)
                        3 -> sectionTime.setBackgroundResource(R.color.bus_maeul)
                        5 -> sectionTime.setBackgroundResource(R.color.bus_airport)
                        6 -> sectionTime.setBackgroundResource(R.color.bus_gansun)
                        11 -> sectionTime.setBackgroundResource(R.color.bus_gansun)
                        14 -> sectionTime.setBackgroundResource(R.color.bus_gwangyeok)
                        26 -> sectionTime.setBackgroundResource(R.color.bus_gansun)
                        else -> sectionTime.setBackgroundResource(R.color.bus_normal)
                    }
                }
                else -> {
                    sectionTime.setBackgroundResource(R.color.walk)
                }
            }
            binding.llPathBar.addView(sectionTime)
        }
    }

    private fun setSubPath(transPath: SearchPubTransPath) {

        val subPathListExceptWalk = ArrayList<SearchPubTransPath.SubPath>()
        for (subPath in transPath.subPathList) {
            if (subPath.trafficType != 3)
                subPathListExceptWalk.add(subPath)
        }
        binding.rcvSubPath.layoutManager = LinearLayoutManager(context)
        binding.rcvSubPath.adapter = SubPathAdapter(subPathListExceptWalk)
    }

    override fun getItemCount(): Int {
        return transPaths.size
    }

    class PathViewHolder(private val binding: ItemPathBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(path: SearchPubTransPath.Path) {
            binding.path = path
            binding.executePendingBindings()
        }
    }
}