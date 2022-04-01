package com.eight_centimeter.android.mvvm_coroutines_hilt

import androidx.multidex.MultiDexApplication
import com.eight_centimeter.android.mvvm_coroutines_hilt.di.appModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : MultiDexApplication() {

    companion object {

        var lang:String = "en"

        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initSteTho()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun initSteTho() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }

}
