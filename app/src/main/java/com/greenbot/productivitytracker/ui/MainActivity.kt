package com.greenbot.productivitytracker.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.greenbot.productivitytracker.LocationListener
import com.greenbot.productivitytracker.PermissionsRequester
import com.greenbot.productivitytracker.R
import com.greenbot.productivitytracker.UserLocation
import com.greenbot.productivitytracker.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity(), LocationListener {

    companion object {
        const val REQUEST_CODE_LOCATION_PERMISSON = 0
    }

    @Inject
    lateinit var permissionRequestor: PermissionsRequester

    @Inject
    lateinit internal var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding? = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        binding?.let {
            it.viewModel = mainViewModel
            it.setLifecycleOwner(this)
        }

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
