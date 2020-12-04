package com.decimalab.simpletask.view.ui.task.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.decimalab.simpletask.R
import com.decimalab.simpletask.data.local.datastore.UserPreferences
import com.decimalab.simpletask.data.remote.model.request.task.AddTaskRequest
import com.decimalab.simpletask.utils.Resource
import com.decimalab.simpletask.utils.ViewUtils.visible
import com.decimalab.simpletask.utils.ViewUtils.warningSnackbar
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_add_task.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import java.util.*

class AddTaskFragment() : Fragment() {

    companion object {
        const val TAG = "AddTaskFragment"
    }

    private val viewModel: AddTaskViewModel by viewModels()
    private var accessToken: String = ""
    private lateinit var userPreferences: UserPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        view.spinner_priorities.onItemSelectedListener = viewModel.priorityColorListener
        view.spinner_status.onItemSelectedListener = viewModel.statusColorListener

        userPreferences = UserPreferences(requireContext())

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        hideProgressBar()
        getAccessToken()
        observeAddTaskResponse()

        fab_add_task.onClick {
            prepareSignUp()
        }
    }

    private fun observeAddTaskResponse() {

        viewModel.addTaskResponse.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> {

                    Log.d(TAG, "Resource.Success" + " Hide Progress")
                    hideProgressBar()

                    if (it.value.status) {

                        requireView().warningSnackbar("Task Added Successfully")

                        et_title.text.clear()
                        et_description.text.clear()


                    } else {

                        Log.d(TAG, "Resource.Success" + it.value.message)
                        toast("Please Enter All The Fields")
                    }

                }
                is Resource.Failure -> {
                    Log.d(TAG, "Resource.Failure" + " Hide Progress")
                    hideProgressBar()
                    toast("Add Task Failed")

                }
                is Resource.Loading -> {
                    Log.d(TAG, "Resource.Loading" + " Show Progress")
                    showProgressBar()

                }
            }
        })
    }

    private fun hideProgressBar() {
        progressbar_add_task.visible(false)
    }

    private fun showProgressBar() {
        progressbar_add_task.visible(true)
    }

    private fun getAccessToken() {

        userPreferences.getAccessToken.asLiveData().observe(viewLifecycleOwner, Observer {
            accessToken = "Bearer $it"
        })
    }

    private fun prepareSignUp() {

        val title = et_title.text.toString().trim()
        val priority = spinner_priorities.selectedItem.toString().trim()
        val status = spinner_status.selectedItem.toString().trim()
        val description = et_description.text.toString().trim()

        when {
            title.isEmpty() && description.isEmpty() -> {
                requireView().warningSnackbar("Please Enter Task Details!")
            }
            title.isEmpty() -> {
                requireView().warningSnackbar("Please Enter Title!")
            }
            description.isEmpty() -> {
                requireView().warningSnackbar("Please Enter Description!")
            }
            else -> {
                val addTaskData = AddTaskRequest(title, description, priority, status)
                viewModel.addTask(accessToken, addTaskData)
            }
        }


    }


}