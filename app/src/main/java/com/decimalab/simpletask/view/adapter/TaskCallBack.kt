package com.decimalab.simpletask.view.adapter

import android.util.Log
import android.view.View

/**
 * Created by Shakil Ahmed Shaj on 04,October,2020.
 * shakilahmedshaj@gmail.com
 */
interface TaskCallBack {
    fun onTaskClick(view: View, position: Int, isLongClick: Boolean)
}