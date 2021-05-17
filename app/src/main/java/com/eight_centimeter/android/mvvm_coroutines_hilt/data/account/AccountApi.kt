package com.eight_centimeter.android.mvvm_coroutines_hilt.data.account

import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.EmployerResponse
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.LoginRequest
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.common.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountApi {

    @POST("/v1/token")
    suspend fun login(@Body param: LoginRequest): BaseResponse<Any>

    @GET("/v2/employers/945e171d-9aab-47c3-8766-b4567627e5c3")
    suspend fun getProfile(): EmployerResponse
}
