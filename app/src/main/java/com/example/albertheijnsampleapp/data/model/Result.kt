package com.example.albertheijnsampleapp.data.model

import java.lang.Exception

/**
 * A sealed class representing the result of an operation.
 */
sealed class Result<out T : Any> {

    /**
     * Represents a successful result with data of type [T].
     *
     * @property data The data associated with the successful result.
     */
    data class Success<out T : Any>(val data: T) : Result<T>()

    /**
     * Represents an error result with an exception.
     *
     * @property exception The exception associated with the error result.
     */
    data class Error(val exception: Exception) : Result<Nothing>()
}
