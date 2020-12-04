package com.decimalab.simpletask.view.ui.auth.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.decimalab.simpletask.R
import com.decimalab.simpletask.base.BaseFragment
import com.decimalab.simpletask.data.remote.model.request.auth.SignUpRequest
import com.decimalab.simpletask.data.remote.network.ApiService
import com.decimalab.simpletask.data.repository.SignUpRepository
import com.decimalab.simpletask.databinding.FragmentSignUpBinding
import com.decimalab.simpletask.utils.Resource
import com.decimalab.simpletask.utils.Validator
import com.decimalab.simpletask.utils.ViewUtils.enable
import com.decimalab.simpletask.utils.ViewUtils.visible
import com.decimalab.simpletask.utils.handleApiError
import com.decimalab.simpletask.utils.network.DataParser
import kotlinx.android.synthetic.main.fragment_add_task.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast

class SignUpFragment : BaseFragment<SignUpViewModel, FragmentSignUpBinding, SignUpRepository>() {
    companion object {
        const val TAG = "SignUpFragment"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        hideProgressBar()
        signInButtonListener()
        observeSignUpResponse()

        binding.btnSignUp.onClick {
            prepareSignUp()
        }
    }

    private fun observeSignUpResponse() {
        viewModel.signUpResponse.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> {


                    Log.d(TAG, "Resource.Success" + " Hide Progress")
                    hideProgressBar()

                    if (it.value.status) {
                        toast("SignUp Success " + it.value.data.name)
                        Navigation.findNavController(requireView())
                            .navigate(R.id.action_signUpFragment_to_loginFragment)
                    } else {

                        toast("User Exists")
                    }

                }
                is Resource.Failure -> {
                    Log.d(TAG, "Resource.Failure" + " Hide Progress")
                    hideProgressBar()
                    handleApiError(it)
                    //toast("SignUp Failed")

                }
                is Resource.Loading -> {
                    Log.d(TAG, "Resource.Loading" + " Show Progress")
                    showProgressBar()

                }
            }
        })
    }

    private fun showProgressBar() {
        binding.progressbarSignUp.visible(true)
    }

    private fun prepareSignUp() {

        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        if (name.isEmpty()) {
            alert {
                isCancelable = false
                title = getString(R.string.empty_user_name_title)
                message = getString(R.string.empty_user_name_msg)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()
        }
        if (email.isEmpty()) {
            alert {
                isCancelable = false
                title = getString(R.string.empty_email_title)
                message = getString(R.string.empty_email_msg)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()
        } else if (password.isEmpty()) {
            alert {
                isCancelable = false
                title = getString(R.string.empty_password_title)
                message = getString(R.string.empty_password_msg)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()

        } else if (confirmPassword.isEmpty()) {
            alert {
                isCancelable = false
                title = getString(R.string.empty_confirm_password_title)
                message = getString(R.string.empty_confirm_password_msg)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()

        } else if (!Validator.validateEmail(email)) {
            alert {
                isCancelable = false
                title = getString(R.string.email_validator_title)
                message = getString(R.string.validation_email_failed)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()
        } else if (!Validator.validatePassword(password)) {
            alert {
                isCancelable = false
                title = getString(R.string.password_validator_title)
                message = getString(R.string.validation_password_failed)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()
        } else if (!Validator.validatePassword(confirmPassword)) {
            alert {
                isCancelable = false
                title = getString(R.string.validation_confirm_password_title)
                message = getString(R.string.validation_confirm_password_failed)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()
        } else if (password != confirmPassword) {
            alert {
                isCancelable = false
                title = getString(R.string.error_confirm_password_title)
                message = getString(R.string.error_confirm_password_msg)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()
        } else {
            val signUpData = SignUpRequest(name, email, password, confirmPassword)

            viewModel.signUp(signUpData)
        }

    }

    private fun hideProgressBar() {
        binding.progressbarSignUp.visible(false)
    }

    private fun signInButtonListener() {
        binding.llSignIn.onClick {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.tvSignIn.onClick {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    override fun getViewModel() = SignUpViewModel::class.java
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSignUpBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        SignUpRepository(remoteDataSource.buildApi(ApiService::class.java))

}