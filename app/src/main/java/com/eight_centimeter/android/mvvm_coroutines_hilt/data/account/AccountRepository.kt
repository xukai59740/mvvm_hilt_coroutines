package com.eight_centimeter.android.mvvm_coroutines_hilt.data.account

import android.util.Log
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountApi: AccountApi
) {

    fun login(): String {
        GlobalScope.launch(Dispatchers.IO + SupervisorJob()) {
            kotlin.runCatching {
                val response = accountApi.login(
                    LoginRequest(
                        "kevin.xu@imaginato.com",
                        "Wsadwsad123"
                    )
                )
                Log.d("kevin", "getUser:${response}")
            }.getOrElse {
                Log.d("kevin", "getUser:${it.message}")
            }
        }
        return "kevin"
    }

}