package com.eight_centimeter.android.mvvm_coroutines_hilt.data.account

import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.LoginRequest
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.common.SharedPrefs
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.BadHttpException
import javax.inject.Inject

class AccountRepository @Inject constructor(
        private val accountApi: AccountApi,
        private val sharedPrefs: SharedPrefs
) {

    suspend fun login(): Any {
        val response = accountApi.login(
                LoginRequest(
                        "asd@test.com",
                        "Wsadwsad123"
                )
        )
        if (response.code != 200) {
            throw BadHttpException(
                    Throwable(),
                    response.code.toString(),
                    response.message
            )

        }
        return response.data
    }

}