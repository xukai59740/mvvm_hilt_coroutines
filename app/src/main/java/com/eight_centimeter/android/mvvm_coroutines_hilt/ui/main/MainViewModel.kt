package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.AccountRepository
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.EmployerResponse
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.BaseViewModel
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Resource
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Result
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

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

    fun test() {
        viewModelScope.launch(Dispatchers.Default) {
            executeFlow(accountRepository.test())
                .onStart { }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            Log.d("kevin", result.data)
                        }
                        is Result.Error -> {
                            Log.d("kevin", result.exception?.message ?: "")
                        }
                    }
                }
        }
    }
}
