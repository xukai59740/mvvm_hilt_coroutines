package com.eight_centimeter.android.mvvm_coroutines_hilt.data.account

import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.LoginRequest
import io.reactivex.Single
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountApi: AccountApi
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