package com.decimalab.simpletask.utils.network

import com.google.gson.JsonParser
import retrofit2.Response

/**
 * Created by Shakil Ahmed Shaj on 30,September,2020.
 * shakilahmedshaj@gmail.com
 */
object DataParser {

    fun handelNetworkError(response: Response<*>): NetworkError {

        val error = response.errorBody()?.string()
        val message = JsonParser().parse(error)
            .asJsonObject["error"]
            .toString()

        return NetworkError(response.code(), message)
    }

    fun handelNetworkMessage(response: String): String {

        return JsonParser().parse(response)
            .asJsonObject["message"]
            .toString()
    }
}