package com.gramzin.cinescope.data.model

import com.gramzin.cinescope.data.room.model.CountriesToFilmEntity
import com.gramzin.cinescope.data.room.model.FilmEntity
import com.gramzin.cinescope.data.room.model.Film
import com.gramzin.cinescope.data.room.model.GenresToFilmEntity
import com.gramzin.cinescope.domain.model.Film as DomainFilm

data class Film(
    val countries: List<Country>?,
    val filmId: Int,
    val filmLength: String?,
    val genres: List<Genre>?,
    val nameEn: String?,
    val nameRu: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val rating: String?,
    val ratingVoteCount: Int?,
    val year: String?
)

data class Country(
    val country: String
)

data class Genre(
    val genre: String
)

fun com.gramzin.cinescope.data.model.Film.toDomain(): DomainFilm{
    return DomainFilm(filmId, nameRu, nameEn,year, filmLength, countries?.map { it.country },
        genres!!.map { it.genre }, rating, posterUrl, posterUrlPreview)
}

fun com.gramzin.cinescope.data.model.Film.toStorage(type: TopFilmCategories): Film {
    val film = FilmEntity(null ,filmId, filmLength, nameEn, nameRu,
        posterUrl, posterUrlPreview, rating, ratingVoteCount, year, type.ordinal, System.currentTimeMillis())
    val countries = countries?.map {
        CountriesToFilmEntity(filmId, it.country)
    } ?: emptyList()

    val genres = genres?.map {
        GenresToFilmEntity(filmId, it.genre)
    } ?: emptyList()

    return Film(film, countries, genres)
}