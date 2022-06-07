package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.AccountRepository
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.EmployerResponse
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.BaseViewModel
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Resource
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Status

class MainViewModel constructor(
    private val savedStateHandle: SavedStateHandle,
    private val accountRepository: AccountRepository,
) : BaseViewModel() {

    val loginLiveData = MutableLiveData<Resource<EmployerResponse>>()

    override fun init() {
        loginLiveData.value = Resource(Status.LOADING)
        asyncRun(
            scope = viewModelScope,
            onRun = {
                accountRepository.login()
                accountRepository.getProfile()
            },
            onResult = {
                it.fold({
                    loginLiveData.value = Resource(Status.SUCCESS, it)
                }) { throwable ->
                    loginLiveData.value = Resource(Status.ERROR, throwable)
                }
            }
        )
    }
}
