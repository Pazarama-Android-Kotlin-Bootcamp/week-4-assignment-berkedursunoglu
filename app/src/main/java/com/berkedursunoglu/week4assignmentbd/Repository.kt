package com.berkedursunoglu.week4assignmentbd

import android.content.Context
import android.util.Log
import com.berkedursunoglu.week4assignmentbd.adapter.WeatherAdapter
import com.berkedursunoglu.week4assignmentbd.api.ApiClient
import com.berkedursunoglu.week4assignmentbd.model.Hourly
import com.berkedursunoglu.week4assignmentbd.model.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    //Get data from service
    fun getWeather(context: Context): Call<WeatherModel> {
        return ApiClient.getApiServices(context).getWeather()
    }

}