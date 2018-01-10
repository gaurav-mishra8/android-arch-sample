package com.greenbot.productivitytracker.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.greenbot.productivitytracker.AppViewModelFactory
import com.greenbot.productivitytracker.GithubViewModelFactory
import com.greenbot.productivitytracker.ui.MainViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by gaurav.mishra on 08/12/17.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: GithubViewModelFactory): ViewModelProvider.Factory

}