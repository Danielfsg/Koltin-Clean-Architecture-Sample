package com.danielfsg.cleanarchitecture;

import android.app.Application
import com.danielfsg.cleanarchitecture.core.di.applicationModule
import com.danielfsg.cleanarchitecture.core.di.moviesModule
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.android.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        injectMembers()
        initializeLeakDetection()
    }

    private fun injectMembers() = startKoin(this, listOf(applicationModule, moviesModule))

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }

}
