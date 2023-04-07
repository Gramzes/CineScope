package com.gramzin.cinescope.data.room.storage

import androidx.room.paging.util.ThreadSafeInvalidationObserver
import androidx.room.withTransaction
import com.gramzin.cinescope.data.model.TopFilmCategories
import com.gramzin.cinescope.data.model.toStorage
import com.gramzin.cinescope.data.room.db.AppDatabase
import com.gramzin.cinescope.data.room.model.toData
import com.gramzin.cinescope.data.storage.FilmLocalStorage
import javax.inject.Inject

class FilmLocalStorageImpl @Inject constructor(
    private val appDatabase: AppDatabase): FilmLocalStorage {

    override suspend fun insertAllFilms(films: List<com.gramzin.cinescope.data.model.Film>, type: TopFilmCategories) {
        val filmsStorage = films.map {
            it.toStorage(type)
        }
        appDatabase.withTransaction {
            appDatabase.filmDao().insertAll(filmsStorage.map { it.film })
            appDatabase.countriesToFilmDao().insertAll(filmsStorage.flatMap { it.countries })
            appDatabase.genresToFilmDao().insertAll(filmsStorage.flatMap { it.genres })
        }

    }

    override suspend fun refreshFilmsByType(films: List<com.gramzin.cinescope.data.model.Film>, type: TopFilmCategories) {
        appDatabase.withTransaction {
            val filmsStorage = films.map { it.toStorage(type) }
            val filmsEntity = filmsStorage.map { it.film }
            val countriesEntity = filmsStorage.flatMap { it.countries }
            val genresEntity = filmsStorage.flatMap { it.genres }

            val deletedFilms = appDatabase.filmDao().refreshFilmsByType(filmsEntity, type.ordinal)
            val deletedFilmIds = deletedFilms.map { it.film.filmId }
            appDatabase.countriesToFilmDao().refreshFilmsByType(deletedFilmIds, countriesEntity)
            appDatabase.genresToFilmDao().refreshFilmsByType(deletedFilmIds, genresEntity)
        }
    }


    override suspend fun getFilmsByType(offset: Int, count: Int, type: TopFilmCategories): List<com.gramzin.cinescope.data.model.Film> {
        return appDatabase
            .filmDao()
            .getFilmsByType(offset, count, type.ordinal)
            .map { it.toData() }
    }

    override suspend fun getLastUpdateTime(type: TopFilmCategories): Long? {
        return appDatabase.filmDao().getLastUpdateTime(type.ordinal)
    }

    override suspend fun getCount(type: TopFilmCategories): Int {
        return appDatabase.filmDao().getCount(type.ordinal)
    }

    override fun registerObserver(observer: ThreadSafeInvalidationObserver) {
        observer.registerIfNecessary(appDatabase)
    }
}
