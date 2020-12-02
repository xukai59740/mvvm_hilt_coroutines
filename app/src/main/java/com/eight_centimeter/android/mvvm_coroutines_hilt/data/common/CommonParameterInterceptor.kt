package com.eight_centimeter.android.mvvm_coroutines_hilt.data.common

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CommonParameterInterceptor(

) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        setAuthHeader(builder, "")
        val newRequest = builder.build()
        return chain.proceed(newRequest)
    }

    private fun setAuthHeader(builder: Request.Builder, token: String?) {
        token?.let { builder.header("Authorization", it) }
    }
}
