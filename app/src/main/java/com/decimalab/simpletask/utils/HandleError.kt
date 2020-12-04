package com.decimalab.simpletask.utils

import androidx.fragment.app.Fragment
import com.decimalab.simpletask.utils.ViewUtils.snackbar
import com.decimalab.simpletask.view.ui.auth.login.LoginFragment
import com.decimalab.simpletask.view.ui.auth.signup.SignUpFragment

/**
 * Created by Shakil Ahmed Shaj on 02,October,2020.
 * shakilahmedshaj@gmail.com
 */

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    when {
        failure.isNetworkError -> requireView().snackbar(
            "Please check your internet connection",
            retry
        )
        failure.errorCode == 401 -> {

                when (this) {
                    is LoginFragment -> {
                        requireView().snackbar("You've Entered Incorrect Email or Password!")
                    }
                    is SignUpFragment -> {
                        requireView().snackbar("User Already Exists!")
                    }
                    else -> {
                        //@todo perform logout operation here
                    }
                }
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}
