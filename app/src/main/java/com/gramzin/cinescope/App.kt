package com.gramzin.cinescope

import android.app.Application
import android.content.Context
import com.gramzin.cinescope.di.components.AppComponent
import com.gramzin.cinescope.di.components.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}

val Context.appComponent: AppComponent
    get(){
        return when(this){
            is App -> appComponent
            else -> applicationContext.appComponent
        }
    }