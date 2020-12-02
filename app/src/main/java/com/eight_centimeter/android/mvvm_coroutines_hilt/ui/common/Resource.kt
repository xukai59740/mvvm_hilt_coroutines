package com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common

open class BaseResource<T, S>(open val status: S) {

    var item: T? = null

    var exception: BaseException? = null
}

class Resource<T>(override val status: Status) : BaseResource<T, Status>(status) {

    constructor(status: Status, item: T) : this(status) {
        this.item = item
    }

    constructor(status: Status, exception: BaseException) : this(status) {
        this.exception = exception
    }
}
