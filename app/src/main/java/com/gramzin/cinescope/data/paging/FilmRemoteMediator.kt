package com.gramzin.cinescope.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.paging.util.getClippedRefreshKey
import com.gramzin.cinescope.data.api.APIUtils
import com.gramzin.cinescope.data.model.TopFilmCategories
import com.gramzin.cinescope.data.model.Film
import com.gramzin.cinescope.data.storage.FilmLocalStorage
import com.gramzin.cinescope.data.storage.FilmRemoteStorage
import retrofit2.HttpException
import java.io.IOException
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.time.toDurationUnit

@OptIn(ExperimentalPagingApi::class)
class FilmRemoteMediator(
    private val filmLocalStorage: FilmLocalStorage,
    private val filmRemoteStorage: FilmRemoteStorage,
    private val type: TopFilmCategories): RemoteMediator<Int, Film>() {

    private var nextPage: Int? = null

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(24, TimeUnit.SECONDS)
        return if (System.currentTimeMillis() - (filmLocalStorage.getLastUpdateTime(type) ?: 0) >= cacheTimeout) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Film>): MediatorResult {
        return try{
             when (loadType) {
                LoadType.REFRESH -> {
                    Log.d("alexkeks", "refresh")
                    nextPage = 1
                }
                LoadType.PREPEND -> {
                    Log.d("alexkeks", "prepend")
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    nextPage = (filmLocalStorage.getCount(type) / state.config.pageSize) + 1
                    Log.d("alexkeks", "load page $nextPage")
                }
            }
            if (nextPage!! > APIUtils.MAX_PAGE){
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            val films = when(type){
                TopFilmCategories.TOP_100_POPULAR_FILMS -> filmRemoteStorage.getPopularFilms(nextPage!!)
                TopFilmCategories.TOP_250_BEST_FILMS -> filmRemoteStorage.getBestFilms(nextPage!!)
                TopFilmCategories.TOP_AWAIT_FILMS -> filmRemoteStorage.getTopAwaitFilms(nextPage!!)
            }
            Log.d("alexkeks", "load success")
            if (loadType == LoadType.REFRESH) {
                filmLocalStorage.refreshFilmsByType(films, type)
                MediatorResult.Success(
                    endOfPaginationReached = films.count() < state.config.pageSize
                )
            }
            else{
                filmLocalStorage.insertAllFilms(films, type)
                MediatorResult.Success(
                    endOfPaginationReached = films.count() < state.config.pageSize
                )
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}