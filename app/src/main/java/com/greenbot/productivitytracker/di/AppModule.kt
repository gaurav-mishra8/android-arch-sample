package com.greenbot.productivitytracker.di

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.greenbot.productivitytracker.ProductivityApp
import com.greenbot.productivitytracker.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by gaurav.mishra on 06/12/17.
 */
@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: ProductivityApp): Context = app.applicationContext

    @Singleton
    @Provides
    fun SharedPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "location.db").build()
    }


}