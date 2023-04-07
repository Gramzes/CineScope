package com.gramzin.cinescope.domain.repository

import androidx.paging.PagingData
import com.gramzin.cinescope.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getBestFilmsPaged(): Flow<PagingData<Film>>
    fun getPopularFilmsPaged(): Flow<PagingData<Film>>
    fun getTopAwaitFilmsPaged(): Flow<PagingData<Film>>
}