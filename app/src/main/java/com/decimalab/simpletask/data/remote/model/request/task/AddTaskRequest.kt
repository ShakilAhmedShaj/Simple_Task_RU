package com.decimalab.simpletask.data.remote.model.request.task


import com.google.gson.annotations.SerializedName

data class AddTaskRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("priority")
    val priority: String,
    @SerializedName("status")
    val status: String

)