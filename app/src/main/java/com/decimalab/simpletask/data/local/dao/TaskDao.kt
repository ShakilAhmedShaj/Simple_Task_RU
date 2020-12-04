package com.decimalab.simpletask.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.decimalab.simpletask.data.local.entity.TaskEntity

/**
 * Created by Shakil Ahmed Shaj on 26,April,2020.
 * shakilahmedshaj@gmail.com
 */
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskEntity: TaskEntity): Long

    @Query("SELECT * FROM task_entity WHERE title LIKE :searchQuery")
    fun searchTask(searchQuery: String): LiveData<List<TaskEntity>>


    @Query("SELECT * FROM task_entity WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntity>>

    @Query("DELETE FROM task_entity")
    suspend fun deleteAll()

}