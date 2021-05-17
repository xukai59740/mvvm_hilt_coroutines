package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.main

import android.os.Bundle
import android.view.KeyEvent
import com.eight_centimeter.android.mvvm_coroutines_hilt.R
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.BaseViewModelActivity
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.SafeObserver
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Status
import com.eight_centimeter.android.mvvm_coroutines_hilt.utils.ProgressDialogUtil
import com.eight_centimeter.android.mvvm_coroutines_hilt.utils.SnackBarUtil
import org.koin.java.KoinJavaComponent

class MainActivity : BaseViewModelActivity<MainViewModel>() {

    companion object {
        const val FIRST_SECOND_EXIT_INTERVAL = 2000
    }

    private var firstClickBackPressTimeMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun buildViewModel(): MainViewModel {
        return KoinJavaComponent.get(MainViewModel::class.java)
    }

    override fun initObserve() {
        viewModel.loginLiveData.observe(this, SafeObserver {

            when (it.status) {
                Status.LOADING -> {
                    ProgressDialogUtil.showProgressDialog(this)
                }
                Status.SUCCESS -> {
                    ProgressDialogUtil.hideProgressDialog()
                    SnackBarUtil().showOkSnack(
                        this,
                        "success"
                    )
                }
                Status.ERROR -> {
                    ProgressDialogUtil.hideProgressDialog()
                    SnackBarUtil().showErrorSnack(
                        this,
                        it.exception?.getErrorMessage()
                    )
                }
            }

        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val secondClick = System.currentTimeMillis()
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondClick - firstClickBackPressTimeMillis < FIRST_SECOND_EXIT_INTERVAL) {
                onBackPressed()
            } else {
                SnackBarUtil().showOkSnack(
                    this,
                    "click again will exit application"
                )
                firstClickBackPressTimeMillis = System.currentTimeMillis()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}