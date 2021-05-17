package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.AccountRepository
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.BaseViewModel
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Resource
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Status


class MainViewModel constructor(
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    val loginLiveData = MutableLiveData<Resource<String>>()
    override fun init() {
        loginLiveData.value = Resource(Status.LOADING)
        asyncRun(
            scope = viewModelScope,
            onRun = {
                accountRepository.login()
            },
            onResult = {
                it.fold({
                    loginLiveData.value = Resource(Status.SUCCESS)
                }) { throwable ->
                    loginLiveData.value = Resource(Status.ERROR, throwable)
                }
            }
        )
    }
}