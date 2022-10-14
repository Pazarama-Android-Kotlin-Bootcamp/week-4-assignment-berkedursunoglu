package com.berkedursunoglu.week4assignmentbd.api

import com.berkedursunoglu.week4assignmentbd.model.WeatherModel
import retrofit2.http.GET

interface ApiService {

    //appid=8ddadecc7ae4f56fee73b2b405a63659
    @GET("onecall?lat=40.99&lon=28.85&lang=tr&exclude=current")
    fun getWeather() : retrofit2.Call<WeatherModel>

}