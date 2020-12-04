package com.decimalab.simpletask.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import com.decimalab.simpletask.data.remote.model.Message
import com.google.gson.JsonParser
import retrofit2.Response

object NetworkStatus {

    fun isNetworkConnected(context: Context): Boolean {
        var result = false
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                result =
                    isCapableNetwork(
                        this,
                        this.activeNetwork
                    )
            } else {
                val networkInfos = this.allNetworks
                for (tempNetworkInfo in networkInfos) {
                    if (isCapableNetwork(
                            this,
                            tempNetworkInfo
                        )
                    )
                        result = true
                }
            }
        }

        return result
    }

    private fun isCapableNetwork(cm: ConnectivityManager, network: Network?): Boolean {
        cm.getNetworkCapabilities(network)?.also {
            if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            }
        }
        return false
    }

}