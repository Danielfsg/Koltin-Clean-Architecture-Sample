package com.danielfsg.cleanarchitecture.core.platform

import android.content.Context
import com.danielfsg.cleanarchitecture.core.extension.networkInfo

class NetworkHandler(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}