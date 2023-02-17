package com.albatros.olimp.common

sealed class Resource<T>(val message: String? = null) {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(message: String? = null) : Resource<T>(message = message)
    class Loading<T>(message: String? = null) : Resource<T>(message = message)
}