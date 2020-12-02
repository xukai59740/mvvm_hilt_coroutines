package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import com.eight_centimeter.android.mvvm_coroutines_hilt.App
import com.eight_centimeter.android.mvvm_coroutines_hilt.R
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.common.BaseResponse
import com.google.gson.Gson
import io.reactivex.functions.Consumer
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException

abstract class CustomErrorConsumer : Consumer<Throwable> {

    override fun accept(throwable: Throwable) {
        if (throwable is BaseException) {
            acceptBaseHttpException(throwable)
        } else {
            acceptBaseHttpException(createBaseException(throwable))
        }
    }

    private fun createBaseException(throwable: Throwable): BaseException {
        return if (throwable is UnknownHostException ||
            throwable is ConnectException ||
            throwable is SocketTimeoutException ||
            throwable is UnknownServiceException
        ) {
            //网络错误
            BaseException(
                throwable,
                "400",
                App.instance.resources.getString(R.string.error_net_work)
            )
        } else {
            // api 返回的错误
            if (throwable is HttpException && throwable.response() != null) {
                // cover errorBody.code and message
                val result =
                    convertRetrofitResponseToBaseException(throwable, throwable.response()!!)
                if (result != null) {
                    return result
                }
            }

            val message = if (throwable.message == null) {
                App.instance.resources.getString(R.string.error_server)
            } else {
                throwable.message
            }
            var code = ""
            if (throwable is HttpException) {
                code = throwable.code().toString()
            }
            BaseException(
                throwable,
                code,
                message
            )
        }
    }

    private fun convertRetrofitResponseToBaseException(
        throwable: Throwable,
        response: Response<*>
    ): BaseException? {
        return try {
            val json = response.errorBody()?.string()
            val baseResponse = Gson().fromJson<BaseResponse<Unit>>(json, BaseResponse::class.java)
            baseResponse?.let {
                BaseException(throwable, it.code.toString(), it.message)
            }
        } catch (e: Exception) {
            RetrofitResponseToBaseException(throwable)
        }
    }

    private fun acceptBaseHttpException(baseException: BaseException) {
        accept(baseException)
    }

    abstract fun accept(it: BaseException)
}
