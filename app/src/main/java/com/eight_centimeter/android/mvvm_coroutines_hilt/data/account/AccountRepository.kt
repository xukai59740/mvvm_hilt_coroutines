package com.eight_centimeter.android.mvvm_coroutines_hilt.data.account

import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.LoginRequest
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.common.SharedPrefs
import io.reactivex.Single
import javax.inject.Inject

class AccountRepository @Inject constructor(
        private val accountApi: AccountApi,
        private val sharedPrefs: SharedPrefs
) {

    fun login(): Single<Any> {
        return accountApi.login(
                LoginRequest(
                        "asd@test.com",
                        "Wsadwsad123"
                )
        ).map {
            it.data
        }
    }

}