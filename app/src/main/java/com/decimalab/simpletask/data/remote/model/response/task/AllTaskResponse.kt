package com.decimalab.simpletask.data.remote.model.response.task


import com.decimalab.simpletask.data.local.entity.TaskEntity
import com.google.gson.annotations.SerializedName

data class AllTaskResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val `data`: List<TaskEntity>
)