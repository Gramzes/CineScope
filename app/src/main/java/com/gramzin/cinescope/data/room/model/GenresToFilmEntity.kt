package com.gramzin.cinescope.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gramzin.cinescope.data.room.DBUtils
import com.gramzin.cinescope.data.room.DBUtils.GenresToFilmTableColumns

@Entity(tableName = DBUtils.genresToFIlmTableName)
data class GenresToFilmEntity(
    @PrimaryKey
    @ColumnInfo(name = GenresToFilmTableColumns.filmId)
    val filmId: Int,
    @ColumnInfo(name = GenresToFilmTableColumns.genre)
    val genre: String)