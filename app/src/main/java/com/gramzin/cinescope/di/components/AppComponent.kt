package com.gramzin.cinescope.di.components

import android.content.Context
import com.gramzin.cinescope.data.api.apiService.FilmApiService
import com.gramzin.cinescope.di.module.NetworkModule
import com.gramzin.cinescope.di.module.RoomModule
import com.gramzin.cinescope.presentation.fragments.FilmListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RoomModule::class])
interface AppComponent {
    fun getApi(): FilmApiService
    fun inject(filmListFragment: FilmListFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}