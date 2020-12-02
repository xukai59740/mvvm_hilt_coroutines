package com.eight_centimeter.android.mvvm_coroutines_hilt.data.account

import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.LoginRequest
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.common.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountApi {

    @POST("/v1/token")
    suspend fun login(@Body param: LoginRequest): BaseResponse<Any>
}
