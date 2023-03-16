package com.gramzin.cinescope.domain.repository

import com.gramzin.cinescope.domain.model.Film

interface FilmRepository {

    fun getBestFilms(page: Int): List<Film>
    fun getPopularFilms(page: Int): List<Film>
    fun getTopAwaitFilms(page: Int): List<Film>
}