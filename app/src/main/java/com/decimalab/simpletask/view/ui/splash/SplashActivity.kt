package com.decimalab.simpletask.view.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.decimalab.simpletask.R
import com.decimalab.simpletask.data.local.datastore.UserPreferences
import com.decimalab.simpletask.utils.Resource
import com.decimalab.simpletask.view.ui.auth.AuthActivity
import com.decimalab.simpletask.utils.ViewUtils
import com.decimalab.simpletask.utils.network.NetworkStatus
import com.decimalab.simpletask.utils.startNewActivity
import com.decimalab.simpletask.view.ui.home.HomeActivity
import kotlinx.coroutines.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class SplashActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SplashActivity"
    }

    private val splashViewModel: SplashViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //hide status bar
        ViewUtils.hideStatusBar(this)
        //check network status
        CoroutineScope(Dispatchers.IO).launch {
            checkNetwork()
        }
    }

    private fun checkUser() {

        val userPreferences = UserPreferences(this)

        userPreferences.getAccessToken.asLiveData().observe(this, Observer {

            if (it.toString() == "null") {
                Log.d(TAG, "New User")
                startNewActivity(AuthActivity::class.java)
            } else {

                observeAccessToken(it)

            }
        })
    }

    private fun observeAccessToken(token: String) {

        Log.d(TAG, "Old User")
        splashViewModel.validateToken("Bearer $token")

        splashViewModel.validateTokenResponse.observe(this, Observer {
            when (it) {
                is Resource.Success -> {

                    Log.d(TAG, "Resource.Success")
                    Log.d(TAG, it.value.message)

                    if (it.value.message == "true") {
                        Log.d(TAG, "Token True")
                        //toast("Login Success Splash")
                        startNewActivity(HomeActivity::class.java)

                    }
                }

                is Resource.Loading -> {
                    Log.d(TAG, "Resource.Loading")

                }

                is Resource.Failure -> {
                    Log.d(TAG, "Resource.Failure")

                    Log.d(TAG, "Token False")
                    toast("Login Failure")
                    startNewActivity(AuthActivity::class.java)

                }
            }
        })
    }

    private suspend fun checkNetwork() {

        delay(2000L)

        val status = NetworkStatus.isNetworkConnected(this)

        if (status) {
            lifecycleScope.launch {

                checkUser()
            }
        } else {
            withContext(Dispatchers.Main) {
                showNetworkAlert()
            }
        }
    }

    private fun showNetworkAlert() {
        alert {
            isCancelable = false
            title = getString(R.string.error_no_internet)
            message = getString((R.string.error_no_internet_msg))
            positiveButton("OK") {
                it.dismiss()
                val intent = Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS)
                startActivity(intent)
            }
        }.show()
    }

    override fun onRestart() {
        super.onRestart()
        CoroutineScope(Dispatchers.IO).launch {
            checkNetwork()
        }
    }
}
