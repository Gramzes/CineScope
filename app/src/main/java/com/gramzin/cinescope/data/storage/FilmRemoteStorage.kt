package com.gramzin.cinescope.data.storage

import com.gramzin.cinescope.data.model.Film

interface FilmRemoteStorage {

    fun getBestFilms(page: Int): List<Film>

    fun getPopularFilms(page: Int): List<Film>

    fun getTopAwaitFilms(page: Int): List<Film>
}