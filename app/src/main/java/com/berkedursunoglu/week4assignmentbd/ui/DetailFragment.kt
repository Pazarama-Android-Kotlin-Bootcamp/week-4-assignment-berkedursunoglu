package com.berkedursunoglu.week4assignmentbd.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.berkedursunoglu.week4assignmentbd.R
import com.berkedursunoglu.week4assignmentbd.adapter.WeatherAdapter
import com.berkedursunoglu.week4assignmentbd.api.ApiClient
import com.berkedursunoglu.week4assignmentbd.databinding.FragmentDetailBinding
import com.berkedursunoglu.week4assignmentbd.model.Hourly
import com.berkedursunoglu.week4assignmentbd.model.WeatherModel
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat


class DetailFragment : Fragment() {
    val df = DecimalFormat("#.#")
    private lateinit var binding: FragmentDetailBinding
    private lateinit var weatherAdapter : WeatherAdapter
    private lateinit var viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        //Provider initialization
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        viewModel.getWeather(requireContext())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        //Degree symbol
        binding.tvWeatherDegreeBase.text = (5).toString() + "°"
        return binding.root
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ViewModel LiveData observe
        viewModel.weatherModel.observe(requireActivity(), Observer {
            weatherAdapter = WeatherAdapter(it?.hourly as ArrayList<Hourly>)
            Glide.with(view).load("http://openweathermap.org/img/wn/${it.hourly.get(0).weather[0].icon}@2x.png").into(binding.ivWeatherIconBase)
            binding.rvWeather.adapter = weatherAdapter
            binding.tvCountry.text = it.timezone
            //Double format
            binding.tvWeatherDegreeBase.text = (df.format((it.hourly[1].temp) - 273)).toString() + "°"
        })
    }

}