package com.decimalab.simpletask.view.ui.task.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.decimalab.simpletask.R
import kotlinx.android.synthetic.main.fragment_task_detail.*
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TaskDetailFragment : Fragment() {

    companion object {
        const val TAG = "TaskDetailFragment"
    }

    private lateinit var viewModel: TaskDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaskDetailViewModel::class.java)


        val args = TaskDetailFragmentArgs.fromBundle(requireArguments())
        viewModel.taskId.value = args.taskId
        viewModel.taskTitle.value = args.taskTitle
        viewModel.taskDescription.value = args.taskDescription
        viewModel.taskStatus.value = args.taskStatus
        viewModel.taskPriority.value = args.taskPriority
        viewModel.taskDate.value = args.taskDate
        viewModel.statusColor.value = args.statusColor

        observeData()

    }

    private fun observeData() {
        viewModel.taskId.observe(viewLifecycleOwner, Observer {
            tv_task_id.text = it.toString()
        })

        viewModel.taskTitle.observe(viewLifecycleOwner, Observer {
            tv_task_title.text = it
        })

        viewModel.taskDescription.observe(viewLifecycleOwner, Observer {
            tv_task_description.text = it
        })

        viewModel.taskStatus.observe(viewLifecycleOwner, Observer {
            tv_status.text = it
        })

        viewModel.taskPriority.observe(viewLifecycleOwner, Observer {
            tv_priority.text = it
        })

        viewModel.taskDate.observe(viewLifecycleOwner, Observer {

            /*
                for api level 26 or up


                val parsed = ZonedDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME)
                val formattedDate = parsed.format(DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm:ss a"))
                tv_date.text = formattedDate.toString()
             */

            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
            val formatter = SimpleDateFormat("EEEE, MMM dd, yyyy hh:mm aa", Locale.US)
            //val formattedDate = formatter.format(parser.parse(it))
            tv_date.text = it.toString()
        })

        viewModel.statusColor.observe(viewLifecycleOwner, Observer {

        })
    }


}