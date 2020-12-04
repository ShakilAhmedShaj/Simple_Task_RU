package com.decimalab.simpletask.view.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decimalab.simpletask.data.remote.model.response.auth.ValidateResponse
import com.decimalab.simpletask.data.remote.network.ApiService
import com.decimalab.simpletask.data.remote.network.RemoteDataSource
import com.decimalab.simpletask.data.repository.SplashRepository
import com.decimalab.simpletask.utils.Resource
import kotlinx.coroutines.launch

/**
 * Created by Shakil Ahmed Shaj on 27,September,2020.
 * shakilahmedshaj@gmail.com
 */
class SplashViewModel() : ViewModel() {

    private val remoteDataSource = RemoteDataSource()
    private val apiService = remoteDataSource.buildApi(ApiService::class.java)
    private val repository = SplashRepository(apiService)


    private val _validateTokenResponse: MutableLiveData<Resource<ValidateResponse>> =
        MutableLiveData()

    val validateTokenResponse: LiveData<Resource<ValidateResponse>>
        get() = _validateTokenResponse


    fun validateToken(token: String) = viewModelScope.launch {
        repository.validateToken(token, _validateTokenResponse)
    }
}