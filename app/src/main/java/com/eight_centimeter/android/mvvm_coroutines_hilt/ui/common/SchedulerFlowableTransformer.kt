package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

class SchedulerFlowableTransformer<T> : FlowableTransformer<T, T> {

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
