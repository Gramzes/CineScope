package com.gramzin.cinescope.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gramzin.cinescope.data.storage.FilmRemoteStorage
import com.gramzin.cinescope.data.model.TopFilmCategories
import com.gramzin.cinescope.data.model.Film

class TopFilmsPagingSource(private val filmRemoteStorage: FilmRemoteStorage, private val category: TopFilmCategories): PagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        Log.d("alexkeks", "load")
        val currentPage = params.key ?: 1
        return try {
            val films = when(category){
                TopFilmCategories.TOP_250_BEST_FILMS -> filmRemoteStorage.getBestFilms(currentPage)
                TopFilmCategories.TOP_100_POPULAR_FILMS -> filmRemoteStorage.getPopularFilms(currentPage)
                TopFilmCategories.TOP_AWAIT_FILMS -> filmRemoteStorage.getTopAwaitFilms(currentPage)
            }

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (films.isEmpty()) null else currentPage + 1

            LoadResult.Page(films, prevPage, nextPage)
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}