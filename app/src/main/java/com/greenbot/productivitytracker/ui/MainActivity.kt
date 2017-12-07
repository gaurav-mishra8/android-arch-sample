package com.greenbot.productivitytracker.ui

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import android.widget.Toast
import com.google.android.gms.tasks.OnSuccessListener
import com.greenbot.productivitytracker.PermissionsRequester
import com.greenbot.productivitytracker.R
import com.greenbot.productivitytracker.UserLocation
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity(), com.greenbot.productivitytracker.LocationListener {

    companion object {
        const val REQUEST_CODE_LOCATION_PERMISSON = 0
    }

    @Inject
    lateinit var permissionRequestor: PermissionsRequester

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java!!)

        mainViewModel.getLocation(this).observe(this, object : Observer<UserLocation> {
            override fun onChanged(userLocation: UserLocation?) {
                updateLocation(userLocation)
            }
        });
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
                Toast.makeText(this, R.string.no_permissions, Toast.LENGTH_LONG).show()
                finish()
                return
            }
        }
    }

    override fun updateLocation(userLocation: UserLocation?) {
        Log.d("MainActivity", "found location ${userLocation?.latitude} and ${userLocation?.longitude}")
    }

}
