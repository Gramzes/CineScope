package com.gramzin.cinescope.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gramzin.cinescope.data.room.DBUtils
import com.gramzin.cinescope.data.room.dao.CountriesToFilmDao
import com.gramzin.cinescope.data.room.dao.FilmDao
import com.gramzin.cinescope.data.room.dao.GenresToFilmDao
import com.gramzin.cinescope.data.room.model.CountriesToFilmEntity
import com.gramzin.cinescope.data.room.model.FilmEntity
import com.gramzin.cinescope.data.room.model.GenresToFilmEntity

@Database(entities = [FilmEntity::class, CountriesToFilmEntity::class,
    GenresToFilmEntity::class], version = DBUtils.version)
abstract class AppDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun countriesToFilmDao(): CountriesToFilmDao
    abstract fun genresToFilmDao(): GenresToFilmDao
}