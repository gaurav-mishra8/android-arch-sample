package com.greenbot.productivitytracker.ui

import android.Manifest
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

/**
 * Created by gaurav.mishra on 06/12/17.
 */
class LocationManager(val context: Context, val lifecycle: Lifecycle, val locationListener: LocationListener) : LifecycleObserver {

    val fusedLocationClient by lazy { LocationServices.getFusedLocationProviderClient(context) }

    init {
        lifecycle.addObserver(this)
    }


    interface LocationListener {
        fun updateLocation(location: Location?)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun registerForLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        val locationRequest = LocationRequest.create()
        val looper = Looper.myLooper()
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, looper)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        fusedLocationClient?.let {
            it.removeLocationUpdates(locationCallback)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unregisterObserver() {
        lifecycle.removeObserver(this)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            val lastLocation = locationResult!!.lastLocation
            locationListener.updateLocation(lastLocation)
        }
    }


}