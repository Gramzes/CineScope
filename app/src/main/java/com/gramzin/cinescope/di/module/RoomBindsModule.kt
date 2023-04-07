package com.gramzin.cinescope.di.module

import com.gramzin.cinescope.data.room.storage.FilmLocalStorageImpl
import com.gramzin.cinescope.data.storage.FilmLocalStorage
import dagger.Binds
import dagger.Module

@Module
interface RoomBindsModule {
    @Binds
    fun filmLocalStorageImpl_to_FilmLocalStorage(
        filmLocalStorageImpl: FilmLocalStorageImpl
    ): FilmLocalStorage
}