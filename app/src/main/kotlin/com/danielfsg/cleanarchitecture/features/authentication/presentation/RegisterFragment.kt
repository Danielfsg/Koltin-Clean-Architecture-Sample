package com.danielfsg.cleanarchitecture.features.authentication.presentation

import android.os.Bundle
import android.view.View
import com.danielfsg.cleanarchitecture.R
import com.danielfsg.cleanarchitecture.core.exception.Failure
import com.danielfsg.cleanarchitecture.core.exception.Failure.NetworkConnection
import com.danielfsg.cleanarchitecture.core.exception.Failure.ServerError
import com.danielfsg.cleanarchitecture.core.extension.failure
import com.danielfsg.cleanarchitecture.core.extension.observe
import com.danielfsg.cleanarchitecture.core.navigation.Navigator
import com.danielfsg.cleanarchitecture.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.ext.android.inject

class RegisterFragment : BaseFragment() {

    private val navigator: Navigator by inject()
    private val registerViewModel: RegisterViewModel by inject()

    override fun layoutId() = R.layout.fragment_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(registerViewModel) {
            observe(loggedUser, ::handleLoggedUser)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLoginClick()
    }

    private fun initLoginClick() {
        showProgress()
        enabledFields(false)
        registerViewModel.registerUser(
            UserView(
                username = et_username.text.toString(),
                email = et_email.text.toString(),
                password = et_password.text.toString()
            )
        )
    }

    private fun enabledFields(enable: Boolean) {
        et_email.isEnabled = enable
        et_password.isEnabled = enable
    }

    private fun handleLoggedUser(isLoggedIn: Boolean?) {
        hideProgress()
        enabledFields(true)
        navigator.showMain(activity!!)
    }

    private fun handleFailure(failure: Failure?) {
        hideProgress()
        enabledFields(true)
        when (failure) {
            is NetworkConnection -> notify(R.string.failure_network_connection)
            is ServerError -> notify(failure.message)
        }
    }

}