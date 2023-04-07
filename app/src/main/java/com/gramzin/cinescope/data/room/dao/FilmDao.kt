package com.gramzin.cinescope.data.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.gramzin.cinescope.data.room.DBUtils
import com.gramzin.cinescope.data.room.DBUtils.FilmTableColumns
import com.gramzin.cinescope.data.room.model.FilmEntity
import com.gramzin.cinescope.data.room.model.Film

@Dao
interface FilmDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(film: List<FilmEntity>)

    @Transaction
    @Query("SELECT * FROM ${DBUtils.filmsTableName} WHERE ${FilmTableColumns.type} = :type")
    suspend fun getAllByType(type: Int): List<Film>

    @Transaction
    @Query("SELECT * FROM ${DBUtils.filmsTableName} WHERE ${FilmTableColumns.type} = :type")
    fun getPagingSource(type: Int): PagingSource<Int, Film>

    @Transaction
    @Query("SELECT * FROM ${DBUtils.filmsTableName} " +
            "WHERE ${FilmTableColumns.type} = :type ORDER BY ${FilmTableColumns.numberInOrder}" +
            " LIMIT :offset, :count")
    suspend fun getFilmsByType(offset: Int, count: Int, type: Int): List<Film>

    @Query("DELETE FROM ${DBUtils.filmsTableName} WHERE ${FilmTableColumns.type} = :type")
    suspend fun clearFilmsByType(type: Int)

    @Transaction
    suspend fun refreshFilmsByType(films: List<FilmEntity>, type: Int): List<Film> {
        val deletedFilms = getAllByType(type)
        clearFilmsByType(type)
        insertAll(films)
        return deletedFilms
    }

    @Query("DELETE FROM ${DBUtils.filmsTableName} WHERE ${FilmTableColumns.type} LIKE :type")
    suspend fun clearAll(type: Int)

    @Query("SELECT max(${FilmTableColumns.insertTime}) FROM ${DBUtils.filmsTableName} " +
            "WHERE ${FilmTableColumns.type} LIKE :type")
    suspend fun getLastUpdateTime(type: Int): Long?

    @Query("SELECT count(*) from ${DBUtils.filmsTableName} WHERE ${FilmTableColumns.type} LIKE :type")
    suspend fun getCount(type: Int): Int
}