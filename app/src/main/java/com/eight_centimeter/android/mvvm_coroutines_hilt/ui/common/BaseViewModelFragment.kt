package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import android.os.Bundle

abstract class BaseViewModelFragment<T : BaseViewModel> : BaseFragment() {

    protected val viewModel by lazy { buildViewModel() }

    protected abstract fun buildViewModel(): T

    protected abstract fun initObserve()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObserve()
        viewModel.init()
    }
}
