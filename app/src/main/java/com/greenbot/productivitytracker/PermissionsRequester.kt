package com.greenbot.productivitytracker

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import com.greenbot.productivitytracker.ui.MainActivity
import java.lang.ref.WeakReference

/**
 * Created by gaurav.mishra on 06/12/17.
 */
class PermissionsRequester(var activityWeakReference: WeakReference<Activity>) {

    val PERMISSIONS = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)

    companion object {
        fun newInstance(activity: Activity): PermissionsRequester {
            val activityWeakReference = WeakReference(activity)
            return PermissionsRequester(activityWeakReference)
        }
    }

    fun hasPermissions(): Boolean {
        val activity = activityWeakReference.get()

        activity?.let {
            for (permission in PERMISSIONS) {
                if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
            return true
        }
        return false
    }

    fun requestPermission() {

        val activity = activityWeakReference.get()

        activity?.let {
            ActivityCompat.requestPermissions(activity, PERMISSIONS, MainActivity.REQUEST_CODE_LOCATION_PERMISSON)
        }

    }


}