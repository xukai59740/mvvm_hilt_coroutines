package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    abstract fun init()

    fun <T> asyncRun(
        context: CoroutineContext = Dispatchers.IO,
        scope: CoroutineScope,
        onRun: suspend () -> T,
        onResult: (Either<BaseException, T>) -> Unit = {}
    ) {
        val job = scope.async(context) { either { onRun.invoke() } }
        scope.launch(Dispatchers.Main) { onResult(job.await()) }
    }
}
