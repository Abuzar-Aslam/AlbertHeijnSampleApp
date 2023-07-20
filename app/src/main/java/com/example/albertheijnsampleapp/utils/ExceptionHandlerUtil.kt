package com.example.albertheijnsampleapp.utils

import com.example.albertheijnsampleapp.R
import retrofit2.HttpException
import java.io.IOException

/**
 * Handles exceptions and returns the corresponding error message resource ID.
 *
 * @param exception The exception to handle.
 * @return The error message resource ID.
 */
fun handleException(exception: Throwable): Int {
    return when (exception) {
        is IOException -> R.string.network_error
        is HttpException -> R.string.http_error
        else -> R.string.unknown_error
    }
}