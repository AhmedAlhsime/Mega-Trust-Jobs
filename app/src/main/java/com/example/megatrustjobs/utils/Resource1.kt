package com.example.megatrustjobs.utils

data class Resource1<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource1<T> = Resource1(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource1<T> =
            Resource1(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource1<T> = Resource1(status = Status.LOADING, data = data, message = null)
    }
}