package com.gramzin.cinescope.data.room

object DBUtils {
    const val version = 1
    const val dbName = "app_db"
    const val countriesToFilmTableName = "countries_to_film"
    const val genresToFIlmTableName = "genres_to_film"
    const val filmsTableName = "films"

    object FilmTableColumns{
        const val numberInOrder = "number_in_order"
        const val filmId= "film_id"
        const val filmLength = "film_length"
        const val nameEn = "name_en"
        const val nameRu = "name_ru"
        const val posterUrl = "poster_url"
        const val posterUrlPreview = "poster_url_preview"
        const val rating = "rating"
        const val ratingVoteCount = "rating_vote_count"
        const val year = "year"
        const val type = "type"
        const val insertTime = "insert_time"
    }

    object CountriesToFilmTableColumns{
        const val filmId = "film_id"
        const val country = "country"
    }

    object GenresToFilmTableColumns{
        const val filmId = "film_id"
        const val genre = "genre"
    }
}