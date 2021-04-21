package com.hafiz.sportworld.core.data

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Berhasil<T>(data: T) : Resource<T>(data)
    class Waiting<T>(data: T? = null) : Resource<T>(data)
    class Exception<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
