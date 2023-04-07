package com.gramzin.cinescope.data.room.model

import androidx.room.Embedded
import androidx.room.Relation
import com.gramzin.cinescope.data.model.Country
import com.gramzin.cinescope.data.model.Film
import com.gramzin.cinescope.data.model.Genre
import com.gramzin.cinescope.data.room.DBUtils

data class Film(
    @Embedded val film: FilmEntity,
    @Relation(
        parentColumn = DBUtils.FilmTableColumns.filmId,
        entityColumn = DBUtils.CountriesToFilmTableColumns.filmId
    )
    val countries: List<CountriesToFilmEntity>,
    @Relation(
        parentColumn = DBUtils.FilmTableColumns.filmId,
        entityColumn = DBUtils.GenresToFilmTableColumns.filmId
    )
    val genres: List<GenresToFilmEntity>
)

fun com.gramzin.cinescope.data.room.model.Film.toData(): Film {
    val countriesData = countries.map { Country(it.country) }
    val genresData = genres.map { Genre(it.genre) }

    return Film(countriesData, film.filmId, film.filmLength,
        genresData, film.nameEn, film.nameRu, film.posterUrl,
    film.posterUrlPreview, film.rating, film.ratingVoteCount, film.year)
}

