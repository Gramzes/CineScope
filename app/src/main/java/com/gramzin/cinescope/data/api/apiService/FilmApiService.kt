package com.gramzin.cinescope.data.api.apiService

import com.gramzin.cinescope.data.api.model.TopFilmResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface FilmApiService {
    companion object{
        private const val KEY = "431e6c4e-3e5d-4bb1-9e6b-cf9db0530120"
    }

    @GET("films/top?type=TOP_250_BEST_FILMS")
    fun getBestFilms(@Query("page") page: Int): TopFilmResponse

    @GET("films/top?type=TOP_100_POPULAR_FILMS")
    fun getPopularFilms(@Query("page") page: Int): TopFilmResponse

    @GET("films/top?type=TOP_AWAIT_FILMS")
    fun getTopAwaitFilms(@Query("page") page: Int): TopFilmResponse
}