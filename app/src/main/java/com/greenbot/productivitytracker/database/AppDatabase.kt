package com.greenbot.productivitytracker.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.greenbot.productivitytracker.database.dao.UserLocationDao
import com.greenbot.productivitytracker.database.entities.UserLocationEntity

/**
 * Created by gaurav.mishra on 06/12/17.
 */
@Database(entities = arrayOf(UserLocationEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userLocationDao(): UserLocationDao
}