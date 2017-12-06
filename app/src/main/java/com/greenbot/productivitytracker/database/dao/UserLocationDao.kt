package com.greenbot.productivitytracker.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.greenbot.productivitytracker.database.entities.UserLocationEntity
import io.reactivex.Single

/**
 * Created by gaurav.mishra on 06/12/17.
 */
@Dao
interface UserLocationDao {

    @Query("SELECT * FROM UserLocation")
    fun getUserLocationHistory(): Single<List<UserLocationEntity>>

    @Insert
    fun insertAll(userLocationEntity: List<UserLocationEntity>)

    @Insert
    fun insert(userLocationEntity: UserLocationEntity)
}