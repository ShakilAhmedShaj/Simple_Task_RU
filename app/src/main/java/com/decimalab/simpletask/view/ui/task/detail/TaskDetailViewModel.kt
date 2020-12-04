package com.decimalab.simpletask.view.ui.task.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskDetailViewModel : ViewModel() {

    val taskId: MutableLiveData<Int> = MutableLiveData()
    val taskTitle: MutableLiveData<String> = MutableLiveData()
    val taskDescription: MutableLiveData<String> = MutableLiveData()
    val taskStatus: MutableLiveData<String> = MutableLiveData()
    val taskPriority: MutableLiveData<String> = MutableLiveData()
    val taskDate: MutableLiveData<String> = MutableLiveData()
    val statusColor: MutableLiveData<String> = MutableLiveData()


}