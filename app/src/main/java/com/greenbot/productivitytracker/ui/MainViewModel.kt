package com.greenbot.productivitytracker.ui

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import com.greenbot.productivitytracker.LocationLiveData
import com.greenbot.productivitytracker.UserLocation
import com.greenbot.productivitytracker.database.AppDatabase
import com.greenbot.productivitytracker.database.entities.UserLocationEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import javax.inject.Inject

/**
 * Created by gaurav.mishra on 07/12/17.
 */
class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var locationLiveData: LocationLiveData


}