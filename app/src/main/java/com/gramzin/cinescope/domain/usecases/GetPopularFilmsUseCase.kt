package com.gramzin.cinescope.domain.usecases

import com.gramzin.cinescope.common.Resource
import com.gramzin.cinescope.domain.model.Film
import com.gramzin.cinescope.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPopularFilmsUseCase @Inject constructor(private val filmRepository: FilmRepository) {
    operator fun invoke(page: Int): Flow<Resource<List<Film>>> = flow{
        try {
            emit(Resource.Loading())
            val films = filmRepository.getPopularFilms(page)
            emit(Resource.Success(films))
        }
        catch (ex: HttpException){
            emit(Resource.Error(ex))
        }
        catch (ex: IOException){
            emit(Resource.Error(ex))
        }
    }
}