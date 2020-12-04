package com.decimalab.simpletask.data.remote.network

import com.decimalab.simpletask.data.remote.model.request.auth.LoginRequest
import com.decimalab.simpletask.data.remote.model.request.auth.SignUpRequest
import com.decimalab.simpletask.data.remote.model.request.task.AddTaskRequest
import com.decimalab.simpletask.data.remote.model.response.auth.ValidateResponse
import com.decimalab.simpletask.data.remote.model.response.auth.login.LoginResponse
import com.decimalab.simpletask.data.remote.model.response.auth.signup.SignUpResponse
import com.decimalab.simpletask.data.remote.model.response.task.AddTaskResponse
import com.decimalab.simpletask.data.remote.model.response.task.AllTaskResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by Shakil Ahmed Shaj on 23,September,2020.
 * shakilahmedshaj@gmail.com
 */
interface ApiService {

    @POST(EndPoints.SIGNUP)
    suspend fun signUp(@Body signUpRequest: SignUpRequest): SignUpResponse

    @POST(EndPoints.LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET(EndPoints.VALIDATE_TOKEN)
    suspend fun validateToken(
        @Header("Authorization") token: String
    ): ValidateResponse

    @POST(EndPoints.ADD_TASK)
    suspend fun addTask(
        @Header("Authorization") token: String,
        @Body addTaskRequest: AddTaskRequest
    ): AddTaskResponse

    @GET(EndPoints.ALL_TASK)
    suspend fun getAllTask(@Header("Authorization") token: String): AllTaskResponse

}