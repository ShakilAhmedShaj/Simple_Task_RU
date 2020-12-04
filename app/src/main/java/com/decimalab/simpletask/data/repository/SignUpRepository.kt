package com.decimalab.simpletask.data.repository

import androidx.lifecycle.MutableLiveData
import com.decimalab.simpletask.base.BaseRepository
import com.decimalab.simpletask.data.remote.model.request.auth.SignUpRequest
import com.decimalab.simpletask.data.remote.model.response.auth.signup.SignUpResponse
import com.decimalab.simpletask.data.remote.network.ApiService
import com.decimalab.simpletask.utils.Resource

/**
 * Created by Shakil Ahmed Shaj on 23,September,2020.
 * shakilahmedshaj@gmail.com
 */
class SignUpRepository(private val apiService: ApiService) : BaseRepository() {

    suspend fun signUp(
        signUpRequest: SignUpRequest,
        liveData: MutableLiveData<Resource<SignUpResponse>>
    ) = safeApiCall({ apiService.signUp(signUpRequest) }, liveData)

}