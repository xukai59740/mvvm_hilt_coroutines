package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    protected abstract fun getContentResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentResource())
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

}
