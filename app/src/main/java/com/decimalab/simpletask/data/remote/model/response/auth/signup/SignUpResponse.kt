package com.decimalab.simpletask.data.remote.model.response.auth.signup


import com.decimalab.simpletask.data.remote.model.Message
import com.decimalab.simpletask.data.remote.model.User
import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: User
)