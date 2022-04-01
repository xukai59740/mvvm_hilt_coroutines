package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatActivity
import com.eight_centimeter.android.mvvm_coroutines_hilt.App
import java.util.Locale

abstract class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.switchLanguageConfig())
    }

    private fun Context?.switchLanguageConfig(): Context? {
        this?.let {
            val config: Configuration = it.resources.configuration
            val locale = Locale(App.lang)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(locale)
                LocaleList.setDefault(localeList)
                config.setLocale(locale)
                config.setLocales(localeList)
                this.createConfigurationContext(config)
            } else {
                config.locale = locale
                resources.updateConfiguration(config, resources.displayMetrics)
                this
            }
        } ?: run {
            return this
        }
    }

    fun Context.updateLanguage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            val config = resources.configuration
            val dm = resources.displayMetrics
            config.locale = Locale(App.lang)
            resources.updateConfiguration(config, dm)
        }
    }
}
