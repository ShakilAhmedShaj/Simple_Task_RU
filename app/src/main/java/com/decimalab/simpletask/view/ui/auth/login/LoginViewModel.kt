package com.decimalab.simpletask.view.ui.auth.login

import androidx.lifecycle.*
import com.decimalab.simpletask.data.remote.model.request.auth.LoginRequest
import com.decimalab.simpletask.data.remote.model.response.auth.login.LoginResponse
import com.decimalab.simpletask.utils.Resource
import com.decimalab.simpletask.data.repository.LoginRepository
import kotlinx.coroutines.launch

/**
 * Created by Shakil Ahmed Shaj on 23,September,2020.
 * shakilahmedshaj@gmail.com
 */
class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(loginRequest: LoginRequest) = viewModelScope.launch {
        repository.login(loginRequest, _loginResponse)
    }

    fun saveUserDetails(loginResponse: LoginResponse) = viewModelScope.launch {
        repository.saveUserDetails(loginResponse)
    }

}
