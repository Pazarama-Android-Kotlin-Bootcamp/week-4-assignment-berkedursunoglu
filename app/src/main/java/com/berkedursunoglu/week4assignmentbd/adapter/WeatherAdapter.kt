package com.berkedursunoglu.week4assignmentbd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.week4assignmentbd.R
import com.berkedursunoglu.week4assignmentbd.databinding.WeatherListRawBinding
import com.berkedursunoglu.week4assignmentbd.model.Hourly
import com.bumptech.glide.Glide
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

class WeatherAdapter(val weatherModel: ArrayList<Hourly>): RecyclerView.Adapter<AdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<WeatherListRawBinding>(inflater, R.layout.weather_list_raw,parent,false)
        return AdapterViewHolder(binding)

    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(weatherModel[position])
    }

    override fun getItemCount(): Int {
        return weatherModel.size
    }
}

class AdapterViewHolder(val binding: WeatherListRawBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(weatherModel: Hourly){
        val df = DecimalFormat("#.#")
        var day = Date(weatherModel.dt* 1000.toLong());
        binding.tvDay.text = day.toString()
        Glide.with(binding.root.context).load("http://openweathermap.org/img/wn/${weatherModel.weather.get(adapterPosition).icon}d@2x.png").into(binding.ivWeatherIconRaw)
        binding.tvWeatherMorningDegree.text = (df.format((weatherModel.temp) - 273)).toString()
        binding.tvWeatherNightDegree.text = (df.format((weatherModel.feels_like) - 273)).toString()
    }
}
