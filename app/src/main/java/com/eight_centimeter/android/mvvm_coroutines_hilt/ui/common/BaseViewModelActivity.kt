package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import android.os.Bundle

abstract class BaseViewModelActivity<T : BaseViewModel> : BaseActivity() {

    protected val viewModel by lazy { buildViewModel() }

    protected abstract fun buildViewModel(): T

    protected abstract fun initObserve()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserve()
        viewModel.init()
    }
}
