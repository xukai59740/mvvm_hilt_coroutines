package com.eight_centimeter.android.mvvm_coroutines_hilt.data.account

import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.LoginRequest
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.common.BaseResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountApi {

    @POST("/v1/token")
    fun login(@Body param: LoginRequest): Single<BaseResponse<Any>>
}
