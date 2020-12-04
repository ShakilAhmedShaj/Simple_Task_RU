package com.decimalab.simpletask.data.remote.model


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("email")
    val email: List<String>
)