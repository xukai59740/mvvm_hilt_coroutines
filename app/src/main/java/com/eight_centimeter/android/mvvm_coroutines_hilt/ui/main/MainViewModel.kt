package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.AccountRepository
import com.eight_centimeter.android.mvvm_coroutines_hilt.extension.customSubscribe
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.BaseViewModel
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Resource
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.SchedulerTransformer
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Status


class MainViewModel @ViewModelInject constructor(
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    val loginLiveData = MutableLiveData<Resource<String>>()
    override fun init() {
        loginLiveData.value = Resource(Status.LOADING)
        accountRepository.login()
            .compose(SchedulerTransformer())
            .customSubscribe({
                loginLiveData.value = Resource(Status.SUCCESS)
            },{
                loginLiveData.value = Resource(Status.ERROR, it)
            })

    }
}