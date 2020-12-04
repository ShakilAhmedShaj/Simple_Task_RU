package com.decimalab.simpletask.data.repository

import androidx.lifecycle.MutableLiveData
import com.decimalab.simpletask.base.BaseRepository
import com.decimalab.simpletask.data.local.datastore.UserPreferences
import com.decimalab.simpletask.data.remote.model.request.auth.LoginRequest
import com.decimalab.simpletask.data.remote.model.response.auth.login.LoginResponse
import com.decimalab.simpletask.data.remote.network.ApiService
import com.decimalab.simpletask.utils.Resource

/**
 * Created by Shakil Ahmed Shaj on 23,September,2020.
 * shakilahmedshaj@gmail.com
 */
class LoginRepository(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        loginRequest: LoginRequest,
        liveData: MutableLiveData<Resource<LoginResponse>>
    ) = safeApiCall({ apiService.login(loginRequest) }, liveData)


    suspend fun saveUserDetails(loginResponse: LoginResponse) {
        userPreferences.saveAccessToken(loginResponse.data.accessToken)
        userPreferences.saveUserID(loginResponse.data.userId.toString())
        userPreferences.saveUserName(loginResponse.data.name)
        userPreferences.saveUserEmail(loginResponse.data.email)
    }


}