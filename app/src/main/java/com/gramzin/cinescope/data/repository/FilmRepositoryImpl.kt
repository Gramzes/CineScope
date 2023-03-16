package com.gramzin.cinescope.data.repository

import com.gramzin.cinescope.data.storage.FilmRemoteStorage
import com.gramzin.cinescope.domain.model.Film
import com.gramzin.cinescope.domain.repository.FilmRepository
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(private val filmRemoteStorage: FilmRemoteStorage): FilmRepository {
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