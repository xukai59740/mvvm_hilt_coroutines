package com.eight_centimeter.android.mvvm_coroutines_hilt

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : MultiDexApplication() {

    companion object {

        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initSteTho()
    }

    private fun initSteTho() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }

}
