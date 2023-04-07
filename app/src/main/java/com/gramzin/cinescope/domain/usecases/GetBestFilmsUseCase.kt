package com.gramzin.cinescope.domain.usecases

import android.util.Log
import androidx.paging.PagingData
import com.gramzin.cinescope.data.room.db.AppDatabase
import com.gramzin.cinescope.data.storage.FilmLocalStorage
import com.gramzin.cinescope.domain.model.Film
import com.gramzin.cinescope.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBestFilmsUseCase @Inject constructor(private val filmRepository: FilmRepository) {
    operator fun invoke(): Flow<PagingData<Film>> {
        return filmRepository.getBestFilmsPaged()
    }
}