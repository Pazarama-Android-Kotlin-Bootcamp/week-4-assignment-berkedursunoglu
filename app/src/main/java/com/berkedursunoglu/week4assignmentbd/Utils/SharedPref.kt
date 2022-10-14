package com.berkedursunoglu.week4assignmentbd.Utils

import android.content.Context
import android.content.SharedPreferences

class SharedPref(val context: Context) {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor:SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences("weather",Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun setKey(key: String){
        sharedPref.edit().putString("key",key).apply()
    }

    fun getKey(key: String): String? {
        val value = sharedPref.getString(key,"ABC")
        return value
    }

}