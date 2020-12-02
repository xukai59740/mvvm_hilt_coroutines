package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import io.reactivex.functions.Consumer

abstract class CustomErrorConsumer : Consumer<Throwable> {

    override fun accept(throwable: Throwable) {
        if (throwable is BaseException) {
            acceptBaseHttpException(throwable)
        } else {
            acceptBaseHttpException(createBaseException(throwable))
        }
    }

    private fun createBaseException(throwable: Throwable): BaseException {
        return BaseException(throwable, "400", "Service error")
    }

    private fun acceptBaseHttpException(baseException: BaseException) {
        accept(baseException)
    }

    abstract fun accept(it: BaseException)
}
