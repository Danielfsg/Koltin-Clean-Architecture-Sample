package com.danielfsg.cleanarchitecture.features.authentication.presentation

import android.content.Context
import android.content.Intent
import com.danielfsg.cleanarchitecture.core.platform.BaseActivity

class RegisterActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context): Intent {
            val intent = Intent(context, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }

    override fun fragment() = RegisterFragment()

}