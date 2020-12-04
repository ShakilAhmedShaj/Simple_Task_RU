package com.decimalab.simpletask.data.repository

import androidx.lifecycle.MutableLiveData
import com.decimalab.simpletask.base.BaseRepository
import com.decimalab.simpletask.data.remote.model.response.auth.ValidateResponse
import com.decimalab.simpletask.data.remote.network.ApiService
import com.decimalab.simpletask.utils.Resource

/**
 * Created by Shakil Ahmed Shaj on 27,September,2020.
 * shakilahmedshaj@gmail.com
 */
class SplashRepository(private val apiService: ApiService) : BaseRepository() {

    suspend fun validateToken(
        accessToken: String,
        liveData: MutableLiveData<Resource<ValidateResponse>>
    ) = safeApiCall({ apiService.validateToken(accessToken) }, liveData)

}