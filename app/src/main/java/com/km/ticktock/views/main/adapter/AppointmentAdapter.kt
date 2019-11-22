package com.km.ticktock.views.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.ticktock.R
import com.km.ticktock.databinding.ItemAppointmentBinding

class AppointmentAdapter(val context: Context?): RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    private lateinit var binding: ItemAppointmentBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AppointmentViewHolder {
        val layoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_appointment, viewGroup, false)

        return AppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = 10

    class AppointmentViewHolder(private val binding: ItemAppointmentBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind() {
        }
    }
}