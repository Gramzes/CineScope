package com.gramzin.cinescope.data.room.dao

import androidx.room.*
import com.gramzin.cinescope.data.room.DBUtils
import com.gramzin.cinescope.data.room.DBUtils.GenresToFilmTableColumns
import com.gramzin.cinescope.data.room.model.CountriesToFilmEntity
import com.gramzin.cinescope.data.room.model.GenresToFilmEntity

@Dao
interface GenresToFilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(genres: List<GenresToFilmEntity>)

    @Query("DELETE FROM ${DBUtils.genresToFIlmTableName}")
    suspend fun clearAll()

    @Query("DELETE FROM ${DBUtils.genresToFIlmTableName}" +
            " WHERE ${GenresToFilmTableColumns.filmId} in (:filmsId)")
    suspend fun clearByFilmIds(filmsId: List<Int>)

    @Transaction
    suspend fun refreshFilmsByType(filmsId: List<Int>, genres: List<GenresToFilmEntity>) {
        clearByFilmIds(filmsId)
        insertAll(genres)
    }
}