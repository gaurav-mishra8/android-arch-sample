package com.greenbot.productivitytracker.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.greenbot.productivitytracker.LocationLiveData
import com.greenbot.productivitytracker.UserLocation
import javax.inject.Inject

/**
 * Created by gaurav.mishra on 07/12/17.
 */
class MainViewModel @Inject constructor() : ViewModel() {

    lateinit var locationLiveData: LiveData<UserLocation>

    fun getLocation(context: Context): LiveData<UserLocation> {
        if (!::locationLiveData.isInitialized) {
            locationLiveData = LocationLiveData(context)
        }
        return locationLiveData
    }


}