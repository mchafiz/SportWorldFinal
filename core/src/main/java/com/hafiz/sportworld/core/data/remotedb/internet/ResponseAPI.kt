package com.hafiz.sportworld.core.data.remotedb.internet

sealed class ResponseAPI<out R> {
    data class Success<out T>(val data: T) : ResponseAPI<T>()
    data class Error(val errorMessage: String) : ResponseAPI<Nothing>()
    object Empty : ResponseAPI<Nothing>()
}