package com.decimalab.simpletask.data.remote.model.response.auth


import com.google.gson.annotations.SerializedName

data class ValidateResponse(
    @SerializedName("message")
    val message: String
)