package com.example.alcoholTracker.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Resource<T>(data = data)
    class Failure<T>(exception: String?): Resource<T>(message = exception)
    class Loading<T>(): Resource<T>()
}