package com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
