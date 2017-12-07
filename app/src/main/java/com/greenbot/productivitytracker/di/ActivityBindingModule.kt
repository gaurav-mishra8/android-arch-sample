package com.greenbot.productivitytracker.di

import com.greenbot.productivitytracker.ui.MainActivity
import com.greenbot.productivitytracker.ui.module.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by gaurav.mishra on 06/12/17.
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun mainActivity(): MainActivity
}