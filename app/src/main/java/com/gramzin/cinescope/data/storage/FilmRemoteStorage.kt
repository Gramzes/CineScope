package com.gramzin.cinescope.data.storage

import com.gramzin.cinescope.data.model.Film

interface FilmRemoteStorage {

    suspend fun getBestFilms(page: Int): List<Film>

    suspend fun getPopularFilms(page: Int): List<Film>

    suspend fun getTopAwaitFilms(page: Int): List<Film>
}