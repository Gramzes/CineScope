package com.gramzin.cinescope.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gramzin.cinescope.data.room.DBUtils
import com.gramzin.cinescope.data.room.DBUtils.CountriesToFilmTableColumns

@Entity(tableName = DBUtils.countriesToFilmTableName)
data class CountriesToFilmEntity(
    @PrimaryKey
    @ColumnInfo(name = CountriesToFilmTableColumns.filmId)
    val filmId: Int,
    @ColumnInfo(name = CountriesToFilmTableColumns.country)
    val country: String
)