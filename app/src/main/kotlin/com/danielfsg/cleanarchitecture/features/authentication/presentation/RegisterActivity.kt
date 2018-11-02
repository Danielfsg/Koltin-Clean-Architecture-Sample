package com.danielfsg.cleanarchitecture.features.authentication.presentation

import android.content.Context
import android.content.Intent
import com.danielfsg.cleanarchitecture.core.platform.BaseActivity

class RegisterActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, RegisterActivity::class.java)
    }

    override fun fragment() = RegisterFragment()

}