package com.decimalab.simpletask.view.ui.task.add

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.decimalab.simpletask.R
import com.decimalab.simpletask.data.local.model.Priority
import com.decimalab.simpletask.data.remote.model.request.task.AddTaskRequest
import com.decimalab.simpletask.data.remote.model.response.task.AddTaskResponse
import com.decimalab.simpletask.data.remote.network.ApiService
import com.decimalab.simpletask.data.remote.network.RemoteDataSource
import com.decimalab.simpletask.data.repository.AddTaskRepository
import com.decimalab.simpletask.data.repository.SplashRepository
import com.decimalab.simpletask.utils.Resource
import kotlinx.coroutines.launch

class AddTaskViewModel(
    application: Application,
) : AndroidViewModel(application) {

    private val remoteDataSource = RemoteDataSource()
    private val apiService = remoteDataSource.buildApi(ApiService::class.java)
    private val repository = AddTaskRepository(apiService)

    private val _addTaskResponse: MutableLiveData<Resource<AddTaskResponse>> = MutableLiveData()

    val addTaskResponse: LiveData<Resource<AddTaskResponse>>
        get() = _addTaskResponse

    fun addTask(
        accessToken: String,
        addTaskRequest: AddTaskRequest
    ) = viewModelScope.launch {
        repository.addTask(accessToken, addTaskRequest, _addTaskResponse)

    }


    val priorityColorListener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when (position) {
                0 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.red
                        )
                    )
                }
                1 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.yellow
                        )
                    )
                }
                2 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.green
                        )
                    )
                }
            }
        }
    }

    val statusColorListener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when (position) {
                0 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.started
                        )
                    )
                }
                1 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.pending
                        )
                    )
                }
                2 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.done
                        )
                    )
                }
            }
        }
    }

    fun parsePriority(priority: String): Priority {
        return when (priority) {
            "High Priority" -> {
                Priority.HIGH
            }
            "Medium Priority" -> {
                Priority.MEDIUM
            }
            "Low Priority" -> {
                Priority.LOW
            }
            else -> Priority.LOW
        }
    }


}