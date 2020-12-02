package com.eight_centimeter.android.mvvm_coroutines_hilt.extension

import android.util.Log
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.BaseException
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.CustomErrorConsumer
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

fun <T> Single<T>.customSubscribe(
    success: (T) -> Unit,
    error: (BaseException) -> Unit
): Disposable {
    return this.subscribe(
        Consumer {
            success(it)
        },
        object : CustomErrorConsumer() {
            override fun accept(it: BaseException) {
                error(it)
            }
        }
    )
}

fun <T> Single<T>.customSubscribe(success: (T) -> Unit): Disposable {
    return this.subscribe(
        Consumer {
            success(it)
        },
        object : CustomErrorConsumer() {
            override fun accept(it: BaseException) {
                Log.d("CustomErrorConsumer", it.toString())
            }
        }
    )
}

fun <T> Flowable<T>.customSubscribe(
    success: (T) -> Unit,
    error: (BaseException) -> Unit
): Disposable {
    return this.subscribe(
        Consumer {
            success(it)
        },
        object : CustomErrorConsumer() {
            override fun accept(it: BaseException) {
                error(it)
            }
        }
    )
}

fun Completable.customSubscribe(error: (BaseException) -> Unit): Disposable {
    return this.subscribe(
        Action {},
        object : CustomErrorConsumer() {
            override fun accept(it: BaseException) {
                error(it)
            }
        }
    )
}
