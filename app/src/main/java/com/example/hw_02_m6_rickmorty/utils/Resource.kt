package com.example.hw_02_m6_rickmorty.utils

sealed class Resource<T> {
    class Loading<T>: Resource<T>()
    class Success<T>(val data: T): Resource<T>()
    class Error<T>(val message: String): Resource<T>()
}