package com.berkedursunoglu.week4assignmentbd.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkedursunoglu.week4assignmentbd.Repository
import com.berkedursunoglu.week4assignmentbd.adapter.WeatherAdapter
import com.berkedursunoglu.week4assignmentbd.api.ApiClient
import com.berkedursunoglu.week4assignmentbd.model.Hourly
import com.berkedursunoglu.week4assignmentbd.model.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel:ViewModel() {

    val weatherModel = MutableLiveData<WeatherModel>()
    val repository = Repository()

    //Get Data
    fun getWeather(context:Context){
       repository.getWeather(context).enqueue(object : Callback<WeatherModel>{
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                Log.d("Response", "Entered")
                if (response.isSuccessful){
                    weatherModel.value = response.body()
                    Log.d("Response", "SUCCESSFUL")
                }else{
                    Log.d("Response", "FAILURE")
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                t.localizedMessage?.let { Log.d("Throwable", it) }
            }
        })
    }

}