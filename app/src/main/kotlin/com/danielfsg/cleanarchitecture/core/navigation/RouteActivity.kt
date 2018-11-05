package com.danielfsg.cleanarchitecture.core.navigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.koin.android.ext.android.inject

class RouteActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.showMain(this)
        finish()
    }
}