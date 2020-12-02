package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.eight_centimeter.android.mvvm_coroutines_hilt.R
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.BaseViewModelActivity
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.SafeObserver
import com.eight_centimeter.android.mvvm_coroutines_hilt.utils.ProgressDialogUtil
import com.eight_centimeter.android.mvvm_coroutines_hilt.utils.SnackBarUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseViewModelActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getContentResource(): Int {
        return R.layout.activity_main
    }

    override fun buildViewModel(): MainViewModel {
        val viewModel: MainViewModel by viewModels()
        return viewModel
    }

    override fun initObserve() {
        viewModel.loginLiveData.observe(this, SafeObserver {
            SnackBarUtil().showSnack(
                this,
                it.item ?: "hahah",
                R.drawable.iv_snack_ok
            ) {}

            //ProgressDialogUtil.showProgressDialog(this)
        })
    }

}