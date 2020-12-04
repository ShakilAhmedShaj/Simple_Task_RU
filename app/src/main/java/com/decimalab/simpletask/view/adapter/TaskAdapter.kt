package com.decimalab.simpletask.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.decimalab.simpletask.R
import com.decimalab.simpletask.data.local.entity.TaskEntity
import com.decimalab.simpletask.databinding.ListTaskBinding

/**
 * Created by Shakil Ahmed Shaj on 03,October,2020.
 * shakilahmedshaj@gmail.com
 */
class TaskAdapter(private val allTaskList: ArrayList<TaskEntity>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private lateinit var taskCallBack: TaskCallBack



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ListTaskBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_task, parent, false
        )

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.d("SHAJ : TaskAdapter", "onBindViewHolder")

        val data: TaskEntity = allTaskList[position]

        when (data.status) {
            "PENDING" -> {
                data.bgColor = R.color.status_pending
            }
            "STARTED" -> {
                data.bgColor = R.color.status_started
            }
            else -> {
                data.bgColor = R.color.status_completed
            }
        }

        holder.listTaskBinding.task = data
        holder.setTaskCallBack(taskCallBack)
    }

    fun setTaskCallBackListener(taskCallBack: TaskCallBack) {
        Log.d("SHAJ : TaskAdapter", "setTaskCallBackListener")
        this.taskCallBack = taskCallBack
    }

    class ViewHolder(binding: ListTaskBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener, View.OnLongClickListener {
        var listTaskBinding: ListTaskBinding = binding

        private lateinit var taskCallBack: TaskCallBack

        init {
            listTaskBinding.root.setOnClickListener(this)
            listTaskBinding.root.setOnLongClickListener(this)
        }

        fun setTaskCallBack(taskCallBack: TaskCallBack) {
            Log.d("SHAJ : TaskAdapter", "setTaskCallBack")
            this.taskCallBack = taskCallBack
        }

        override fun onClick(view: View?) {
            if (view != null) {
                taskCallBack.onTaskClick(view, adapterPosition, false)
            }
        }

        override fun onLongClick(view: View?): Boolean {
            if (view != null) {
                taskCallBack.onTaskClick(view, adapterPosition, true)
            }

            return false
        }
    }

    override fun getItemCount(): Int {
        return allTaskList.size
    }
}