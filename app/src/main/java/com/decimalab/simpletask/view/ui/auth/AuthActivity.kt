package com.decimalab.simpletask.view.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.decimalab.simpletask.R
import com.decimalab.simpletask.utils.ViewUtils

class AuthActivity : AppCompatActivity() {

    companion object {
        const val TAG = "AuthActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //hide status bar
        ViewUtils.hideStatusBar(this)

    }
}