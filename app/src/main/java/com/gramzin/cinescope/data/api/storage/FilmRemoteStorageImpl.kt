package com.gramzin.cinescope.data.api.storage

import com.gramzin.cinescope.data.api.apiService.FilmApiService
import com.gramzin.cinescope.data.api.model.toRepository
import com.gramzin.cinescope.data.model.Film
import com.gramzin.cinescope.data.storage.FilmRemoteStorage
import javax.inject.Inject

class FilmRemoteStorageImpl @Inject constructor(private val service: FilmApiService): FilmRemoteStorage {
    override suspend fun getBestFilms(page: Int): List<Film> {
        return service.getBestFilms(page).films!!.map { it.toRepository() }
    }

    override suspend fun getPopularFilms(page: Int): List<Film> {
        return service.getPopularFilms(page).films!!.map { it.toRepository() }
    }

    override suspend fun getTopAwaitFilms(page: Int): List<Film> {
        return service.getTopAwaitFilms(page).films!!.map { it.toRepository() }
    }
}