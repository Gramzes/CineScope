package com.gramzin.cinescope.di.module

import com.gramzin.cinescope.data.api.storage.FilmRemoteStorageImpl
import com.gramzin.cinescope.data.repository.FilmRepositoryImpl
import com.gramzin.cinescope.data.storage.FilmRemoteStorage
import com.gramzin.cinescope.domain.repository.FilmRepository
import dagger.Binds
import dagger.Module

@Module
interface NetworkBindsModule {

    @Binds
    fun filmRemoteStorageImpl_to_FilmRemoteStorage(
        filmRemoteStorageImpl: FilmRemoteStorageImpl
    ): FilmRemoteStorage

    @Binds
    fun filmRepositoryImpl_to_FilmRepository(
        filmRepositoryImpl: FilmRepositoryImpl
    ): FilmRepository
}