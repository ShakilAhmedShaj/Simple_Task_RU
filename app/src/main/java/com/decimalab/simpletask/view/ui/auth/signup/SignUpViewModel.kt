package com.decimalab.simpletask.view.ui.auth.signup

import androidx.lifecycle.*
import com.decimalab.simpletask.data.remote.model.request.auth.SignUpRequest
import com.decimalab.simpletask.data.remote.model.response.auth.signup.SignUpResponse
import com.decimalab.simpletask.utils.Resource
import com.decimalab.simpletask.data.repository.SignUpRepository
import kotlinx.coroutines.launch

/**
 * Created by Shakil Ahmed Shaj on 23,September,2020.
 * shakilahmedshaj@gmail.com
 */
class SignUpViewModel(private val repository: SignUpRepository) : ViewModel() {

    private val _signUpResponse: MutableLiveData<Resource<SignUpResponse>> = MutableLiveData()

    val signUpResponse: LiveData<Resource<SignUpResponse>>
        get() = _signUpResponse

    fun signUp(signUpRequest: SignUpRequest) = viewModelScope.launch {
        repository.signUp(signUpRequest, _signUpResponse)

    }
}
