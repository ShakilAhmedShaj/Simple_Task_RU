package com.decimalab.simpletask.utils

import android.app.Activity
import android.content.Intent

/**
 * Created by Shakil Ahmed Shaj on 25,September,2020.
 * shakilahmedshaj@gmail.com
 */

fun<A : Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

