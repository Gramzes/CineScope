package com.gramzin.cinescope.data.api.model

import com.google.gson.annotations.SerializedName
import com.gramzin.cinescope.data.model.Film as RepoFilm
import com.gramzin.cinescope.data.model.Country as RepoCountry
import com.gramzin.cinescope.data.model.Genre as RepoGenre

data class TopFilmResponse(
    @SerializedName("films")
    val films: List<Film>?,
    @SerializedName("pagesCount")
    val pagesCount: Int?
)

data class Film(
    @SerializedName("countries")
    val countries: List<Country>?,
    @SerializedName("filmId")
    val filmId: Int,
    @SerializedName("filmLength")
    val filmLength: String?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("nameEn")
    val nameEn: String?,
    @SerializedName("nameRu")
    val nameRu: String?,
    @SerializedName("posterUrl")
    val posterUrl: String?,
    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("ratingChange")
    val ratingChange: Any?,
    @SerializedName("ratingVoteCount")
    val ratingVoteCount: Int?,
    @SerializedName("year")
    val year: String?
)

data class Country(
    @SerializedName("country")
    val country: String
)

data class Genre(
    @SerializedName("genre")
    val genre: String
)

fun Country.toRepository(): RepoCountry{
    return RepoCountry(country)
}

fun Genre.toRepository(): RepoGenre{
    return RepoGenre(genre)
}
fun Film.toRepository(): RepoFilm{
    val countries = countries?.map { it.toRepository() }
    val genres = genres?.map { it.toRepository() }
    return RepoFilm(countries, filmId, filmLength, genres, nameEn, nameRu,
        posterUrl, posterUrlPreview, rating, ratingVoteCount, year)
}