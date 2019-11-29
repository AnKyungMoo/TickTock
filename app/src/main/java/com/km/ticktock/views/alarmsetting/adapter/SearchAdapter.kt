package com.km.ticktock.views.alarmsetting.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.ticktock.R
import com.km.ticktock.databinding.ItemLocationSearchBinding
import com.km.ticktock.views.alarmsetting.model.KeywordObject

class SearchAdapter(val context: Context) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var locationList = arrayListOf<KeywordObject.documents>()
    lateinit var binding: ItemLocationSearchBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_location_search, parent, false)

        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(locationList[position])
    }

    fun clearList() {
        locationList.clear()
        notifyDataSetChanged()
    }

    fun addLocation(item: KeywordObject.documents) {
        locationList.add(item)
        notifyDataSetChanged()
    }

    fun addLocations(items: ArrayList<KeywordObject.documents>) {
        locationList = items
        notifyDataSetChanged()
    }

    class SearchViewHolder(private val binding: ItemLocationSearchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: KeywordObject.documents) {
            binding.txtLocationTitle.text = item.place_name
            binding.txtLocationDetail.text = item.address_name
        }
    }
}