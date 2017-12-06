package com.greenbot.productivitytracker.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by gaurav.mishra on 06/12/17.
 */
@Entity(tableName = "UserLocation")
class UserLocationEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long = 0;

    @ColumnInfo(name = "latitude")
    var lat: Double = 0.0

    @ColumnInfo(name = "longitude")
    var long: Double = 0.0

    @ColumnInfo(name = "address")
    var address: String? = null

}