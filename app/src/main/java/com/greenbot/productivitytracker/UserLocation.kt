package com.greenbot.productivitytracker

import java.io.Serializable
import javax.inject.Inject

/**
 * Created by gaurav.mishra on 07/12/17.
 */
data class UserLocation (val latitude: Double, val longitude: Double, val accuracy: Float) : Serializable
