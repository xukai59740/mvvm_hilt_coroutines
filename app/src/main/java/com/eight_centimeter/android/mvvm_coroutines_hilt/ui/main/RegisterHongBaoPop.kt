package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.main

import android.content.Context
import com.eight_centimeter.android.mvvm_coroutines_hilt.R
import com.lxj.xpopup.core.CenterPopupView
import kotlinx.android.synthetic.main.pop_register_hongbao.view.*


class RegisterHongBaoPop(context: Context) : CenterPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.pop_register_hongbao
    }

    override fun onCreate() {
        super.onCreate()
        tvBingWeChat.setOnClickListener {

        }

        tvReceive.setOnClickListener {

        }

        tvShare.setOnClickListener {

        }

        tvRules.setOnClickListener {

        }
    }
}