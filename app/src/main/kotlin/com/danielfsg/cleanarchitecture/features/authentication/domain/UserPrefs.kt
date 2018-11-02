package com.danielfsg.cleanarchitecture.features.authentication.domain

import com.danielfsg.cleanarchitecture.core.platform.SharedPrefsHandler
import com.google.gson.Gson

class UserPrefs(private val sharedPrefsHandler: SharedPrefsHandler) {

    private val loggedUserKey = "loggedUserKey"

    var loggedUser: User?
        get() = Gson().fromJson(sharedPrefsHandler.prefs.getString(loggedUserKey, ""), User::class.java)
        set(value) = sharedPrefsHandler.prefs.edit().putString(loggedUserKey, Gson().toJson(value)).apply()

}