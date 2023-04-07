package com.gramzin.cinescope.data.storage

import androidx.room.paging.util.ThreadSafeInvalidationObserver
import com.gramzin.cinescope.data.model.TopFilmCategories

interface FilmLocalStorage {

    suspend fun insertAllFilms(films: List<com.gramzin.cinescope.data.model.Film>, type: TopFilmCategories)

    suspend fun refreshFilmsByType(films: List<com.gramzin.cinescope.data.model.Film>, type: TopFilmCategories)

    suspend fun getFilmsByType(offset: Int, count: Int, type: TopFilmCategories): List<com.gramzin.cinescope.data.model.Film>

    suspend fun getLastUpdateTime(type: TopFilmCategories): Long?

    suspend fun getCount(type: TopFilmCategories): Int

    fun registerObserver(observer: ThreadSafeInvalidationObserver)
}