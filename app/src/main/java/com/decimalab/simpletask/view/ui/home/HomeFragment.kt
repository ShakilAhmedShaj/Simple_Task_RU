package com.decimalab.simpletask.view.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decimalab.simpletask.R
import com.decimalab.simpletask.data.local.datastore.UserPreferences
import com.decimalab.simpletask.data.local.entity.TaskEntity
import com.decimalab.simpletask.databinding.FragmentHomeBinding
import com.decimalab.simpletask.utils.Resource
import com.decimalab.simpletask.utils.ViewUtils.visible
import com.decimalab.simpletask.view.adapter.TaskAdapter
import com.decimalab.simpletask.view.adapter.TaskCallBack
import org.jetbrains.anko.support.v4.toast

class HomeFragment : Fragment(), TaskCallBack, SearchView.OnQueryTextListener {

    companion object {
        const val TAG = "HomeFragment"
    }


    private val viewModel: HomeViewModel by viewModels()
    private var accessToken: String = ""

    private var allTaskList: ArrayList<TaskEntity> = ArrayList()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        recyclerView = binding.rvTask
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager


        val userPreferences = UserPreferences(requireContext())

        userPreferences.getAccessToken.asLiveData().observe(viewLifecycleOwner, Observer {
            accessToken = "Bearer $it"
            Log.d(TAG, accessToken)
            viewModel.getAllTask(accessToken)
        })
        //viewModel.checkIfDatabaseEmpty(allTaskList)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideProgressBar()
        observeGetAllTaskResponse()


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_fragment_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchThroughDatabase(query)
        }
        return true
    }

    private fun searchThroughDatabase(query: String) {
        val searchQuery = "%$query%"
        allTaskList.clear()
        viewModel.searchDatabase(searchQuery).observe(this, Observer { list ->
            list?.let {

                allTaskList = it.toCollection(allTaskList)
                setRecyclerView()
            }
        })


    }

    private fun observeGetAllTaskResponse() {

        viewModel.getAllTaskResponse.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> {

                    if (it.value.status) {

                        allTaskList.clear()
                        allTaskList = it.value.data.toCollection(allTaskList)
                        viewModel.cacheTask(allTaskList)
                        setRecyclerView()
                        //viewModel.checkIfDatabaseEmpty(allTaskList)

                        hideProgressBar()
                        //toast("Loaded")

                        Log.d(TAG, "All Tasks : $allTaskList")
                    } else {
                        hideProgressBar()
                        toast("No Task")
                    }


                }
                is Resource.Failure -> {

                    hideProgressBar()
                    toast("Failed")
                    //handleApiError(it)

                }
                is Resource.Loading -> {
                    showProgressBar()

                }
            }
        })
    }

    private fun setRecyclerView() {



        taskAdapter = TaskAdapter(allTaskList)
        recyclerView.adapter = taskAdapter

        taskAdapter.setTaskCallBackListener(this)

    }

    override fun onTaskClick(view: View, position: Int, isLongClick: Boolean) {
        if (isLongClick) {
            Log.d(TAG, "Long CLick: $position")
        } else {

            val data = allTaskList[position]

            findNavController().navigate(
                HomeFragmentDirections.actionNavMainToTaskDetailFragment(
                    data.taskId,
                    data.title,
                    data.body.toString(),
                    data.status.toString(),
                    data.priority,
                    data.createdAt.toString(),
                    data.bgColor.toString(),
                )
            )
            Log.d(TAG, "CLick: $position")

        }
    }

    private fun hideProgressBar() {
        binding.progressbarHome.visible(false)
    }

    private fun showProgressBar() {
        binding.progressbarHome.visible(true)
    }


}