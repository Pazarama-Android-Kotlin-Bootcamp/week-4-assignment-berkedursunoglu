package com.berkedursunoglu.week4assignmentbd.api

import android.app.Application
import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient:Application() {

    companion object{
        private lateinit var apiServices: ApiService

        fun getApiServices(context: Context): ApiService {
            if(!::apiServices.isInitialized) {
                val refrofit = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .client(getHttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiServices = refrofit.create(ApiService::class.java)
            }
            return apiServices
        }


        private fun getHttpClient(context: Context): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(Interceptor(context))
            httpClient.connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            httpClient.readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            httpClient.writeTimeout(90, java.util.concurrent.TimeUnit.SECONDS)
            return httpClient.build()
        }
    }


}