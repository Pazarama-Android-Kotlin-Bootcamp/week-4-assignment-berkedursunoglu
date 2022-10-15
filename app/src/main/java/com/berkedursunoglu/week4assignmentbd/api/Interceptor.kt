package com.berkedursunoglu.week4assignmentbd.api

import android.content.Context
import android.util.Log
import com.berkedursunoglu.week4assignmentbd.Utils.SharedPref
import okhttp3.Interceptor
import okhttp3.Response

class Interceptor(val context: Context):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val key = SharedPref(context)
        val keyValue = key.getKey("key")
        Log.d("Response", keyValue.toString())
        val request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("appid",keyValue).build()
        val response = request.newBuilder().url(url).build()
        return chain.proceed(response)
    }


}