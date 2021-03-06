package com.decimalab.simpletask.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.decimalab.simpletask.base.BaseRepository
import com.decimalab.simpletask.data.local.dao.TaskDao
import com.decimalab.simpletask.data.local.db.AppDatabase
import com.decimalab.simpletask.data.local.entity.TaskEntity
import com.decimalab.simpletask.data.remote.model.response.task.AllTaskResponse
import com.decimalab.simpletask.data.remote.network.ApiService
import com.decimalab.simpletask.utils.Resource

/**
 * Created by Shakil Ahmed Shaj on 02,October,2020.
 * shakilahmedshaj@gmail.com
 */
class HomeRepository(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val taskDao: TaskDao
) : BaseRepository() {

    suspend fun getAllTask(
        accessToken: String, liveData: MutableLiveData<Resource<AllTaskResponse>>
    ) = safeApiCall({ apiService.getAllTask(accessToken) }, liveData)

    suspend fun insert(taskEntities: List<TaskEntity>) {

        /*for (task in taskEntities){
            val tempTask = TaskEntity(
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
            )*/
        //taskDao.deleteAll()
        taskDao.insert(
            taskEntities
        )
    }


    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntity>> {
        return taskDao.searchDatabase(searchQuery)
    }

    fun getAllTaskList(): LiveData<List<TaskEntity>> {
        return taskDao.getAllTaskList()
    }

    suspend fun deleteAll() {
        taskDao.deleteAll()
    }

}