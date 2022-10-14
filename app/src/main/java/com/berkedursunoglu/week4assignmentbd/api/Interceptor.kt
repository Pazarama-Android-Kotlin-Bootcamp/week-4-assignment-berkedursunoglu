package com.berkedursunoglu.week4assignmentbd.api

import android.content.Context
import com.berkedursunoglu.week4assignmentbd.Utils.SharedPref
import okhttp3.Interceptor
import okhttp3.Response

class Interceptor(val context: Context):Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val key = SharedPref(context)
        val keyValue = key.getKey("key")
        val request = chain.request()
        val response = request.newBuilder().header("appid",keyValue).build()

        return chain.proceed(response)
    }


}