package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    protected fun Disposable.collect() = compositeDisposable.add(this)

    abstract fun init()

    fun cleared() {
        onCleared()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
