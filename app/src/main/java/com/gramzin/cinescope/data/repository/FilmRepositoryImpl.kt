package com.gramzin.cinescope.data.repository

import androidx.paging.*
import com.gramzin.cinescope.data.api.APIUtils
import com.gramzin.cinescope.data.model.TopFilmCategories
import com.gramzin.cinescope.data.model.toDomain
import com.gramzin.cinescope.data.storage.FilmRemoteStorage
import com.gramzin.cinescope.domain.model.Film as DomainFilm
import com.gramzin.cinescope.data.paging.FilmRemoteMediator
import com.gramzin.cinescope.data.paging.TopFilmsLocalPagingSource
import com.gramzin.cinescope.data.room.model.toData
import com.gramzin.cinescope.data.storage.FilmLocalStorage
import com.gramzin.cinescope.domain.repository.FilmRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val filmRemoteStorage: FilmRemoteStorage,
    private val filmLocalStorage: FilmLocalStorage): FilmRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getBestFilmsPaged(): Flow<PagingData<DomainFilm>> {
        return Pager(PagingConfig(pageSize = APIUtils.PAGE_SIZE, initialLoadSize = 20, prefetchDistance = 10),
        remoteMediator = FilmRemoteMediator(filmLocalStorage,
            filmRemoteStorage, TopFilmCategories.TOP_250_BEST_FILMS)){
            TopFilmsLocalPagingSource(filmLocalStorage, TopFilmCategories.TOP_250_BEST_FILMS)
        }.flow.toDomain()
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularFilmsPaged(): Flow<PagingData<DomainFilm>> {
        return Pager(PagingConfig(pageSize = APIUtils.PAGE_SIZE, initialLoadSize = 20, prefetchDistance = 10),
            remoteMediator = FilmRemoteMediator(filmLocalStorage,
                filmRemoteStorage, TopFilmCategories.TOP_100_POPULAR_FILMS)){
            TopFilmsLocalPagingSource(filmLocalStorage, TopFilmCategories.TOP_100_POPULAR_FILMS)
        }.flow.toDomain()
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopAwaitFilmsPaged(): Flow<PagingData<DomainFilm>> {
        return Pager(PagingConfig(pageSize = APIUtils.PAGE_SIZE, initialLoadSize = 20, prefetchDistance = 5),
            remoteMediator = FilmRemoteMediator(filmLocalStorage,
                filmRemoteStorage, TopFilmCategories.TOP_AWAIT_FILMS)){
            TopFilmsLocalPagingSource(filmLocalStorage, TopFilmCategories.TOP_AWAIT_FILMS)
        }.flow.toDomain()
    }
}

fun Flow<PagingData<com.gramzin.cinescope.data.model.Film>>.toDomain(): Flow<PagingData<DomainFilm>> {
    return transform { value ->
        emit(value.map {
            it.toDomain()
        })
    }
}
