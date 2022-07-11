package com.cesa.searchappcesa.data

sealed class Result<out T> {

    data class Success<out T>(val data: T): Result<T>()
    data class Error<out T>(val data: T?, val exception: Exception): Result<T>()
    object Loading : Result<Nothing>()

}