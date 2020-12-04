package com.decimalab.simpletask.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.decimalab.simpletask.data.repository.LoginRepository
import com.decimalab.simpletask.data.repository.SignUpRepository
import com.decimalab.simpletask.view.ui.auth.login.LoginViewModel
import com.decimalab.simpletask.view.ui.auth.signup.SignUpViewModel
import java.lang.IllegalArgumentException

/**
 * Created by Shakil Ahmed Shaj on 23,September,2020.
 * shakilahmedshaj@gmail.com
 */
class BaseViewModelFactory {

    @Suppress("UNCHECKED_CAST")
    class ViewModelFactory(
        private val repository: BaseRepository
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository as LoginRepository) as T
                modelClass.isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(repository as SignUpRepository) as T
                else -> throw IllegalArgumentException("ViewModelClass Not Found")
            }
        }
    }

}
