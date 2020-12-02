package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import java.io.IOException

class BaseException(
    private var throwable: Throwable,
    private var code: String? = null,
    private var errorMessage: String? = null
) : IOException(throwable) {

    fun getCode(): String? {
        return code
    }

    fun getErrorMessage(): String? {
        return errorMessage
    }
}