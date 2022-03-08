package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import com.eight_centimeter.android.mvvm_coroutines_hilt.R
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.EmployerResponse
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.BaseViewModelActivity
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.SafeObserver
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Status
import com.eight_centimeter.android.mvvm_coroutines_hilt.utils.ProgressDialogUtil
import com.eight_centimeter.android.mvvm_coroutines_hilt.utils.SnackBarUtil
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseViewModelActivity<MainViewModel>() {

    companion object {
        const val FIRST_SECOND_EXIT_INTERVAL = 2000
    }

    private var firstClickBackPressTimeMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvHelloWorld.setOnClickListener {
            showPop()
        }
    }

    private fun showPop(){
        XPopup.setStatusBarBgColor(Color.parseColor("#00000000"))
        XPopup.setShadowBgColor(Color.parseColor("#00000000"))
        XPopup.Builder(this)
            .isLightStatusBar(true)
            .popupAnimation(PopupAnimation.TranslateFromBottom)
            .asCustom(RegisterHongBaoPop(this))
            .show()
    }

    override fun buildViewModel(): MainViewModel {
        val viewModels: MainViewModel by viewModel()
        return viewModels
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