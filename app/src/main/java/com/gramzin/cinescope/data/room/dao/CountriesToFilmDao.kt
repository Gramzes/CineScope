package com.gramzin.cinescope.data.room.dao

import androidx.room.*
import com.gramzin.cinescope.data.room.DBUtils
import com.gramzin.cinescope.data.room.DBUtils.CountriesToFilmTableColumns
import com.gramzin.cinescope.data.room.model.CountriesToFilmEntity

@Dao
interface CountriesToFilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<CountriesToFilmEntity>)

    @Query("DELETE FROM ${DBUtils.countriesToFilmTableName}")
    suspend fun clearAll()

    @Query("DELETE FROM ${DBUtils.countriesToFilmTableName}" +
            " WHERE ${CountriesToFilmTableColumns.country} in (:filmsId)")
    suspend fun clearByFilmIds(filmsId: List<Int>)

    @Transaction
    suspend fun refreshFilmsByType(filmsId: List<Int>, countries: List<CountriesToFilmEntity>) {
        clearByFilmIds(filmsId)
        insertAll(countries)
    }
}