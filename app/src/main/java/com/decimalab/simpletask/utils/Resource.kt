package com.decimalab.simpletask.utils

import okhttp3.ResponseBody

/**
 * Created by Shakil Ahmed Shaj on 23,September,2020.
 * shakilahmedshaj@gmail.com
 */
sealed class Resource<out T> {

    object Loading : Resource<Nothing>()
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()
}