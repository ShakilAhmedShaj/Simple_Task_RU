package com.decimalab.simpletask.view.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.decimalab.simpletask.R
import com.decimalab.simpletask.data.local.datastore.UserPreferences
import com.decimalab.simpletask.utils.startNewActivity
import com.decimalab.simpletask.view.ui.auth.AuthActivity
import com.decimalab.simpletask.view.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    companion object {
        const val TAG = "ProfileFragment"
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)



        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val userPreferences = UserPreferences(requireContext())

        userPreferences.getUserName.asLiveData().observe(viewLifecycleOwner, Observer {

            if (it.toString() == "null") {
                Log.d(TAG, "Loading Error!")

            } else {

                tv_name.text = it

            }
        })

        userPreferences.getUserEmail.asLiveData().observe(viewLifecycleOwner, Observer {

            if (it.toString() == "null") {
                Log.d(TAG, "Loading Error!")

            } else {

                tv_email.text = it

            }
        })
    }

}