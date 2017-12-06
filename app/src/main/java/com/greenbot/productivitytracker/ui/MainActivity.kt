package com.greenbot.productivitytracker.ui

import android.Manifest
import android.content.Intent
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.LocationServices
import android.content.pm.PackageManager
import android.location.LocationListener
import android.os.PersistableBundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.google.android.gms.tasks.OnSuccessListener
import com.greenbot.productivitytracker.PermissionsRequester
import com.greenbot.productivitytracker.R
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity(), LocationManager.LocationListener {

    companion object {
        const val REQUEST_CODE_LOCATION_PERMISSON = 0
    }

    val permissionRequestor by lazy { PermissionsRequester.newInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LocationManager(context = this, lifecycle = lifecycle, locationListener = this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
    }

    override fun onStart() {
        super.onStart()

        if (!permissionRequestor.hasPermissions()) {
            permissionRequestor.requestPermission()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for (grantResult in grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                finish()
                return
            }
        }
    }

    override fun updateLocation(location: Location?) {
        Log.d("MainActivity", "found location ${location?.latitude} and ${location?.longitude}")
    }

}
