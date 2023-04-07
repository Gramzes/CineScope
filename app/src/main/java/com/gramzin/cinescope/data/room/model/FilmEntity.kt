package com.gramzin.cinescope.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gramzin.cinescope.data.room.DBUtils
import com.gramzin.cinescope.data.room.DBUtils.FilmTableColumns

@Entity(tableName = DBUtils.filmsTableName)
data class FilmEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = FilmTableColumns.numberInOrder)
    val numberInOrder: Int?,
    @ColumnInfo(name = FilmTableColumns.filmId)
    val filmId: Int,
    @ColumnInfo(name = FilmTableColumns.filmLength)
    val filmLength: String?,
    @ColumnInfo(name = FilmTableColumns.nameEn)
    val nameEn: String?,
    @ColumnInfo(name = FilmTableColumns.nameRu)
    val nameRu: String?,
    @ColumnInfo(name = FilmTableColumns.posterUrl)
    val posterUrl: String?,
    @ColumnInfo(name = FilmTableColumns.posterUrlPreview)
    val posterUrlPreview: String?,
    @ColumnInfo(name = FilmTableColumns.rating)
    val rating: String?,
    @ColumnInfo(name = FilmTableColumns.ratingVoteCount)
    val ratingVoteCount: Int?,
    @ColumnInfo(name = FilmTableColumns.year)
    val year: String?,
    @ColumnInfo(name = FilmTableColumns.type)
    val type: Int,
    @ColumnInfo(name = FilmTableColumns.insertTime)
    val insertTime: Long
)