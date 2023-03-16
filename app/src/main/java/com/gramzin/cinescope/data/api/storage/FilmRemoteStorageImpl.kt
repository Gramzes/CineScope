package com.gramzin.cinescope.data.api.storage

import com.gramzin.cinescope.data.api.apiService.FilmApiService
import com.gramzin.cinescope.data.model.Film
import com.gramzin.cinescope.data.storage.FilmRemoteStorage
import javax.inject.Inject

class FilmRemoteStorageImpl @Inject constructor(private val service: FilmApiService): FilmRemoteStorage {
    override fun getBestFilms(page: Int): List<Film> {
        TODO("Not yet implemented")
    }

    override fun getPopularFilms(page: Int): List<Film> {
        TODO("Not yet implemented")
    }

    override fun getTopAwaitFilms(page: Int): List<Film> {
        TODO("Not yet implemented")
    }
}