package com.gramzin.cinescope.presentation.fragments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gramzin.cinescope.data.model.TopFilmCategories
import com.gramzin.cinescope.domain.model.Film
import com.gramzin.cinescope.domain.usecases.GetBestFilmsUseCase
import com.gramzin.cinescope.domain.usecases.GetPopularFilmsUseCase
import com.gramzin.cinescope.domain.usecases.GetTopAwaitFilmsUseCase
import kotlinx.coroutines.flow.Flow

class FilmListViewModel(
    private val type: Int,
    private val getBestFilmsUseCase: GetBestFilmsUseCase,
    private val getPopularFilmsUseCase: GetPopularFilmsUseCase,
    private val getTopAwaitFilmsUseCase: GetTopAwaitFilmsUseCase): ViewModel()
{
    val filmFlow: Flow<PagingData<Film>> = when(type){
        TopFilmCategories.TOP_250_BEST_FILMS.ordinal -> getBestFilmsUseCase()
        TopFilmCategories.TOP_100_POPULAR_FILMS.ordinal -> getPopularFilmsUseCase()
        else -> getTopAwaitFilmsUseCase()
    }.cachedIn(viewModelScope)


}