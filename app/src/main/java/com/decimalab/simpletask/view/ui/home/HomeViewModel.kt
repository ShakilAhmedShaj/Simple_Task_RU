package com.decimalab.simpletask.view.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.decimalab.simpletask.data.local.db.AppDatabase
import com.decimalab.simpletask.data.local.entity.TaskEntity
import com.decimalab.simpletask.data.remote.model.response.task.AllTaskResponse
import com.decimalab.simpletask.data.remote.network.ApiService
import com.decimalab.simpletask.data.remote.network.RemoteDataSource
import com.decimalab.simpletask.data.repository.HomeRepository
import com.decimalab.simpletask.utils.Resource
import kotlinx.coroutines.launch

/**
 * Created by Shakil Ahmed Shaj on 27,September,2020.
 * shakilahmedshaj@gmail.com
 */
class HomeViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TAG = "HomeViewModel"
    }


    private val taskDao = AppDatabase.getInstance(application).taskDao()
    private val remoteDataSource = RemoteDataSource()
    private val apiService = remoteDataSource.buildApi(ApiService::class.java)
    private val repository =
        HomeRepository(apiService, AppDatabase.getInstance(application), taskDao)


    private val _getAllTaskResponse: MutableLiveData<Resource<AllTaskResponse>> =
        MutableLiveData()

    val getAllTaskResponse: LiveData<Resource<AllTaskResponse>>
        get() = _getAllTaskResponse


    fun cacheTask(taskList: ArrayList<TaskEntity>) = viewModelScope.launch {
        repository.deleteAll()

        for (task in taskList) {
            val id = repository.insert(
                TaskEntity(
                    title = task.title,
                    body = task.body,
                    priority = task.priority,
                    status = task.status,
                    userId = task.userId,
                    updatedAt = task.updatedAt,
                    createdAt = task.createdAt,
                    taskId = task.taskId,
                    bgColor = task.bgColor,
                    id = task.id
                )
            )
            Log.d(TAG, "cacheTask: $id")
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntity>> {
        return repository.searchDatabase(searchQuery)
    }


    fun getAllTask(token: String) = viewModelScope.launch {
        repository.getAllTask(token, _getAllTaskResponse)


    }
}