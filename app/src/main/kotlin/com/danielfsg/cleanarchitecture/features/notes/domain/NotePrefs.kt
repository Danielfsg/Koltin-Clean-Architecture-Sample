package com.danielfsg.cleanarchitecture.features.notes.domain

import com.danielfsg.cleanarchitecture.core.platform.SharedPrefsHandler

class NotePrefs (private val sharedPrefsHandler: SharedPrefsHandler) {

    private val jwTokenKey = "jwTokenKey"

    var jwToken: String?
        get() = "Bearer " + sharedPrefsHandler.prefs.getString(jwTokenKey, "")
        set(value) = sharedPrefsHandler.prefs.edit().putString(jwTokenKey, value).apply()

}