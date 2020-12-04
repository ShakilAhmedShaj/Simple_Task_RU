package com.decimalab.simpletask.data.repository

import androidx.lifecycle.MutableLiveData
import com.decimalab.simpletask.base.BaseRepository
import com.decimalab.simpletask.data.remote.model.request.task.AddTaskRequest
import com.decimalab.simpletask.data.remote.model.response.task.AddTaskResponse
import com.decimalab.simpletask.data.remote.network.ApiService
import com.decimalab.simpletask.utils.Resource

/**
 * Created by Shakil Ahmed Shaj on 02,October,2020.
 * shakilahmedshaj@gmail.com
 */
class AddTaskRepository(
    private val apiService: ApiService
) : BaseRepository() {

    suspend fun addTask(
        accessToken: String,
        addTaskRequest: AddTaskRequest,
        liveData: MutableLiveData<Resource<AddTaskResponse>>
    ) = safeApiCall({ apiService.addTask(accessToken, addTaskRequest) }, liveData)

}