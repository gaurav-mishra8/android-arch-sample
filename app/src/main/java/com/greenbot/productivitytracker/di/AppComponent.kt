package com.greenbot.productivitytracker.di

import com.greenbot.productivitytracker.ProductivityApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by gaurav.mishra on 06/12/17.
 */
@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityBindingModule::class))
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: ProductivityApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: ProductivityApp)


}