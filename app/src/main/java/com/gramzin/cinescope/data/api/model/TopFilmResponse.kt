package com.gramzin.cinescope.data.api.model

import com.google.gson.annotations.SerializedName

data class TopFilmResponse(
    @SerializedName("films")
    val films: List<Film?>?,
    @SerializedName("pagesCount")
    val pagesCount: Int?
)

data class Film(
    @SerializedName("countries")
    val countries: List<Country?>?,
    @SerializedName("filmId")
    val filmId: Int?,
    @SerializedName("filmLength")
    val filmLength: String?,
    @SerializedName("genres")
    val genres: List<Genre?>?,
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
    val country: String?
)

data class Genre(
    @SerializedName("genre")
    val genre: String?
)