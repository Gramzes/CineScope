package com.gramzin.cinescope.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.paging.util.ThreadSafeInvalidationObserver
import com.gramzin.cinescope.data.model.Film
import com.gramzin.cinescope.data.model.TopFilmCategories
import com.gramzin.cinescope.data.room.DBUtils
import com.gramzin.cinescope.data.storage.FilmLocalStorage

class TopFilmsLocalPagingSource(
    private val filmLocalStorage: FilmLocalStorage,
    private val type: TopFilmCategories): PagingSource<Int, Film>() {

    private val observer = ThreadSafeInvalidationObserver(
        tables = arrayOf(DBUtils.filmsTableName),
        onInvalidated = ::invalidate
    )

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        Log.d("alexkeks", "source: refresh")
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        filmLocalStorage.registerObserver(observer)

        val currentPage = params.key ?: 1
        Log.d("alexkeks", "source: load page $currentPage")
        val offset = (currentPage - 1) * params.loadSize
        val count = params.loadSize

        return try {
            val films = filmLocalStorage.getFilmsByType(offset, count, type)
            val prevKey = if (currentPage == 1) null else currentPage - 1
            val nextKey = if (films.count() < count) null else currentPage + 1

            LoadResult.Page(films, prevKey, nextKey)
        } catch (ex: Exception){
            LoadResult.Error(ex)
        }
    }

    class Factory( private val filmLocalStorage: FilmLocalStorage,
                   private val type: TopFilmCategories) {
        fun create(): TopFilmsLocalPagingSource{
            return TopFilmsLocalPagingSource(filmLocalStorage, type)
        }
    }

}