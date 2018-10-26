package com.danielfsg.cleanarchitecture.features.movies.presentation

import android.content.Context
import android.content.Intent
import com.danielfsg.cleanarchitecture.core.platform.BaseActivity

class MoviesActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }

    override fun fragment() = MoviesFragment()

}