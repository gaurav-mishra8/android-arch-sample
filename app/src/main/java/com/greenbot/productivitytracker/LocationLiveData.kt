package com.greenbot.productivitytracker

import android.Manifest
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.*

/**
 * Created by gaurav.mishra on 07/12/17.
 */
class LocationLiveData(val context: Context) : MediatorLiveData<UserLocation>() {

    val fusedLocationProvider: FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(context) }

    override fun onActive() {
        super.onActive()

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        val locationRequest = LocationRequest.create()
        val looper = Looper.myLooper()

        fusedLocationProvider.requestLocationUpdates(locationRequest, locationCallback, looper)

    }

    val locationCallback = object : LocationCallback() {

        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            locationResult?.let {
                val newLocation = locationResult.lastLocation
                val latitude = newLocation.latitude
                val longitude = newLocation.longitude
                val accuracy = newLocation.accuracy
                value = UserLocation(latitude, longitude, accuracy)
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        fusedLocationProvider.removeLocationUpdates(locationCallback)
    }
}