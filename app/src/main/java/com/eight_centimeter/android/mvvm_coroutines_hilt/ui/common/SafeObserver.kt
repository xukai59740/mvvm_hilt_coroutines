package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import androidx.lifecycle.Observer

class SafeObserver<T>(private val notifier: (T) -> Unit) : Observer<T> {
    override fun onChanged(t: T?) {
        t?.let { notifier(t) }
    }
}
