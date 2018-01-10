package com.greenbot.productivitytracker

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass


/**
 * Created by gaurav.mishra on 08/12/17.
 */
@Suppress("UNCHECKED_CAST")
@Singleton
class AppViewModelFactory @Inject constructor(val creators: Map<KClass<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {


        val kclass: KClass<out ViewModel> = modelClass.javaClass.kotlin as KClass<out ViewModel>

        val creator: Provider<out ViewModel>? = creators.get(kclass) ?: throw IllegalArgumentException("unknown model class " + modelClass)

        return creator?.get() as T

    }
}
