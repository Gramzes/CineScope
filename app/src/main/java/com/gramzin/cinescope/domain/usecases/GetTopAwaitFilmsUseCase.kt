package com.gramzin.cinescope.domain.usecases

import android.util.Log
import androidx.paging.PagingData
import com.gramzin.cinescope.common.Resource
import com.gramzin.cinescope.data.room.db.AppDatabase
import com.gramzin.cinescope.domain.model.Film
import com.gramzin.cinescope.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTopAwaitFilmsUseCase @Inject constructor(private val filmRepository: FilmRepository) {
    operator fun invoke(): Flow<PagingData<Film>> {
        return filmRepository.getTopAwaitFilmsPaged()
    }
}