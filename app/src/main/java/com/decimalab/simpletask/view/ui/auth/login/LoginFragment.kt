package com.decimalab.simpletask.view.ui.auth.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.decimalab.simpletask.R
import com.decimalab.simpletask.data.remote.model.request.auth.LoginRequest
import com.decimalab.simpletask.data.remote.network.ApiService
import com.decimalab.simpletask.utils.Resource
import com.decimalab.simpletask.data.repository.LoginRepository
import com.decimalab.simpletask.databinding.FragmentLoginBinding
import com.decimalab.simpletask.base.BaseFragment
import com.decimalab.simpletask.utils.Validator
import com.decimalab.simpletask.utils.ViewUtils.enable
import com.decimalab.simpletask.utils.ViewUtils.visible
import com.decimalab.simpletask.utils.handleApiError
import com.decimalab.simpletask.utils.startNewActivity
import com.decimalab.simpletask.view.ui.home.HomeActivity
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast

class LoginFragment :
    BaseFragment<LoginViewModel, FragmentLoginBinding, LoginRepository>() {

    companion object {
        const val TAG = "LoginFragment"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        hideConditionalViews()
        loginButtonEnabler()
        observeLoginResponse()
        loginButtonListener()
    }

    private fun loginButtonListener() {
        binding.btnLogin.onClick { prepareLogin() }
        binding.llSignUp.onClick {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.tvSignUp.onClick {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }


    private fun observeLoginResponse() {
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> {

                    Log.d(TAG, "Resource.Success" + " Hide Progress " + it.value.toString())
                    hideProgressBar()

                    if (it.value.status) {

                        viewModel.saveUserDetails(it.value)
                        requireActivity().startNewActivity(HomeActivity::class.java)

                        toast("Login Success")
                    } else {

                        toast(it.value.message.toString())
                    }
                }

                is Resource.Loading -> {
                    Log.d(TAG, "Resource.Loading" + " Show Progress")
                    showProgressBar()
                }

                is Resource.Failure ->
                {
                    Log.d(TAG, "Resource.Failure" + " Hide Progress")
                    hideProgressBar()
                    handleApiError(it)
                }
            }
        })
    }

    private fun loginButtonEnabler() {
        binding.etPassword.addTextChangedListener() {
            val email = binding.etEmail.text.toString().trim()
            binding.btnLogin.enable(
                email.isNotEmpty() && it.toString().isNotEmpty()
            )
        }
    }

    private fun prepareLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (!Validator.validateEmail(email)) {


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
        } else {
            val loginData = LoginRequest(email, password)

            viewModel.login(loginData)
        }

    }

    private fun hideConditionalViews() {
        Log.d(TAG, "TOP" + " Hide Progress")
        hideProgressBar()

        binding.btnLogin.enable(false)
    }

    private fun hideProgressBar() {
        binding.progressbarLogin.visible(false)
    }

    private fun showProgressBar() {
        binding.progressbarLogin.visible(true)
    }

    override fun getViewModel() = LoginViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        LoginRepository(remoteDataSource.buildApi(ApiService::class.java), userPreferences)
}