package com.danielfsg.cleanarchitecture.core.platform

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson

class SharedPrefsHandler(context: Context) {

    private val prefsFilename = "kotlin-test"
    val prefs: SharedPreferences = context.getSharedPreferences(prefsFilename, MODE_PRIVATE)

}