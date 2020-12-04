package com.decimalab.simpletask.data.remote.model.response.auth.login


import com.decimalab.simpletask.data.remote.model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: User
)