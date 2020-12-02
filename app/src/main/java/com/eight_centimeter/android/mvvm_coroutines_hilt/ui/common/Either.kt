package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Either.Error
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Either.Success

/**
 *  Represents a value from two possible types. Instances of
 *  [Either] are either an [Error] or a [Success]
 */
sealed class Either<out E, out V> {
    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Success<out V>(val value: V) : Either<Nothing, V>()

    val isError get() = this is Error<E>
    val isSuccess get() = this is Success<V>

    fun <E> error(error: E) = Error(error)
    fun <V> success(value: V) = Success(value)

    fun fold(fnSuccess: (V) -> Unit, fnError: (E) -> Unit): Unit =
        when (this) {
            is Success -> fnSuccess(value)
            is Error -> fnError(error)
        }
}

suspend fun <V> either(block: suspend () -> V): Either<BaseException, V> =
    runCatching {
        Either.Success(block())
    }.getOrElse {
        Either.Error(CoroutinesError.accept(it))
    }

fun <E, V> Either<E, V>.errorValue(): E? = when (this) {
    is Either.Error<E> -> this.error
    else -> null
}

fun <E, V> Either<E, V>.successValue(): V? = when (this) {
    is Either.Success<V> -> this.value
    else -> null
}
