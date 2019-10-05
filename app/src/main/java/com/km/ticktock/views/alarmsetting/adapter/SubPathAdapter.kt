package com.km.ticktock.views.alarmsetting.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.ticktock.R
import com.km.ticktock.databinding.ItemSubPathBinding
import com.km.ticktock.views.alarmsetting.entity.SearchPubTransPath

class SubPathAdapter(private val subPathList: ArrayList<SearchPubTransPath.SubPath>): RecyclerView.Adapter<SubPathAdapter.SubPathViewHolder>() {

    private lateinit var binding: ItemSubPathBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SubPathViewHolder {
        val layoutInflater = viewGroup.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_sub_path, viewGroup, false)

        return SubPathViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubPathViewHolder, position: Int) {

        holder.bind(subPathList[position])
        setSubPathImage(position)
        setSubPathText(position)
    }

    private fun setSubPathImage(position: Int) {

        when (subPathList[position].trafficType) {
            1 -> {
                binding.tvSubPathNo.visibility = View.GONE
                when (subPathList[position].lane.subwayCode) {
                    1 -> binding.ivImage.setImageResource(R.drawable.icn_subway_1)
                    2 -> binding.ivImage.setImageResource(R.drawable.icn_subway_2)
                    3 -> binding.ivImage.setImageResource(R.drawable.icn_subway_3)
                    4 -> binding.ivImage.setImageResource(R.drawable.icn_subway_4)
                    5 -> binding.ivImage.setImageResource(R.drawable.icn_subway_5)
                    6 -> binding.ivImage.setImageResource(R.drawable.icn_subway_6)
                    7 -> binding.ivImage.setImageResource(R.drawable.icn_subway_7)
                    8 -> binding.ivImage.setImageResource(R.drawable.icn_subway_8)
                    9 -> binding.ivImage.setImageResource(R.drawable.icn_subway_9)
                    100 -> binding.ivImage.setImageResource(R.drawable.icn_subway_bundang)
                    101 -> binding.ivImage.setImageResource(R.drawable.icn_subway_airport)
                    102 -> binding.ivImage.setImageResource(R.drawable.icn_subway_zaki)
                    104 -> binding.ivImage.setImageResource(R.drawable.icn_subway_gyeong_ui)
                    107 -> binding.ivImage.setImageResource(R.drawable.icn_subway_ever)
                    108 -> binding.ivImage.setImageResource(R.drawable.icn_subway_gyeong_chun)
                    109 -> binding.ivImage.setImageResource(R.drawable.icn_subway_sin)
                    110 -> binding.ivImage.setImageResource(R.drawable.icn_subway_uijeong)
                    111 -> binding.ivImage.setImageResource(R.drawable.icn_subway_suin)
                    112 -> binding.ivImage.setImageResource(R.drawable.icn_subway_gyeonggang)
                    113 -> binding.ivImage.setImageResource(R.drawable.icn_subway_wui)
                    114 -> binding.ivImage.setImageResource(R.drawable.icn_subway_seohae)
                    21 -> binding.ivImage.setImageResource(R.drawable.icn_subway_in_1)
                    22 -> binding.ivImage.setImageResource(R.drawable.icn_subway_in_2)
                    31 -> binding.ivImage.setImageResource(R.drawable.icn_subway_daejeon_1)
                    41 -> binding.ivImage.setImageResource(R.drawable.icn_subway_dae_1)
                    42 -> binding.ivImage.setImageResource(R.drawable.icn_subway_dae_2)
                    43 -> binding.ivImage.setImageResource(R.drawable.icn_subway_dae_3)
                    51 -> binding.ivImage.setImageResource(R.drawable.icn_subway_gwangju)
                    71 -> binding.ivImage.setImageResource(R.drawable.icn_subway_busan_1)
                    72 -> binding.ivImage.setImageResource(R.drawable.icn_subway_busan_2)
                    73 -> binding.ivImage.setImageResource(R.drawable.icn_subway_busan_3)
                    74 -> binding.ivImage.setImageResource(R.drawable.icn_subway_busan_4)
                    78 -> binding.ivImage.setImageResource(R.drawable.icn_subway_donghae)
                    79 -> binding.ivImage.setImageResource(R.drawable.icn_subway_bukim)
                    else -> binding.ivImage.setImageResource(R.drawable.icn_subway_1)
                }
            }
            2 -> {
                binding.tvSubPathNo.text = subPathList[position].lane.busNo
                when (subPathList[position].lane.type) {
                    1 -> binding.ivImage.setImageResource(R.drawable.icn_bus_normal)
                    3 -> binding.ivImage.setImageResource(R.drawable.icn_bus_village)
                    5 -> binding.ivImage.setImageResource(R.drawable.icn_bus_airport)
                    6 -> binding.ivImage.setImageResource(R.drawable.icn_bus_line)
                    11 -> binding.ivImage.setImageResource(R.drawable.icn_bus_line)
                    14 -> binding.ivImage.setImageResource(R.drawable.icn_bus_wide)
                    26 -> binding.ivImage.setImageResource(R.drawable.icn_bus_line)
                    else -> binding.ivImage.setImageResource(R.drawable.icn_bus_normal)
                }
            }
        }
    }

    private fun setSubPathText(position: Int) {

        if (position == 0 && position == itemCount-1) {
            binding.tvSubPath.text = SubPathBindingAdapter.setSubPath(subPathList[position], isFirst = true, isLast = true)
        } else if (position == 0 && position < itemCount-1) {
            binding.tvSubPath.text = SubPathBindingAdapter.setSubPath(subPathList[position], isFirst = true, isLast = false)
        } else if (position > 0 && position < itemCount-1) {
            binding.tvSubPath.text = SubPathBindingAdapter.setSubPath(subPathList[position], isFirst = false, isLast = false)
        } else { // position > 0 && position == itemCount-1
            binding.tvSubPath.text = SubPathBindingAdapter.setSubPath(subPathList[position], isFirst = false, isLast = true)
        }
    }

    override fun getItemCount(): Int {
        return subPathList.size
    }

    class SubPathViewHolder(private val binding: ItemSubPathBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(subPath: SearchPubTransPath.SubPath) {
            binding.subPath = subPath
            binding.executePendingBindings()
        }
    }
}