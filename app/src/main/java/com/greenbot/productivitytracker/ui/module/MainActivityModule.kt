package com.greenbot.productivitytracker.ui.module

import android.arch.lifecycle.MutableLiveData
import com.greenbot.productivitytracker.LocationLiveData
import com.greenbot.productivitytracker.PermissionsRequester
import com.greenbot.productivitytracker.UserLocation
import com.greenbot.productivitytracker.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by gaurav.mishra on 06/12/17.
 */
@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindMainViewModel(locationLiveData: LocationLiveData): LocationLiveData

}