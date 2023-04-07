package com.gramzin.cinescope.presentation.fragments.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gramzin.cinescope.domain.usecases.GetBestFilmsUseCase
import com.gramzin.cinescope.domain.usecases.GetPopularFilmsUseCase
import com.gramzin.cinescope.domain.usecases.GetTopAwaitFilmsUseCase
import com.gramzin.cinescope.presentation.fragments.FilmListFragment
import com.gramzin.cinescope.presentation.fragments.viewModel.FilmListViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("UNCHECKED_CAST")
class FilmListViewModelFactory @AssistedInject constructor(
    @Assisted("type") private val type: Int,
    private val getBestFilmsUseCase: GetBestFilmsUseCase,
    private val getPopularFilmsUseCase: GetPopularFilmsUseCase,
    private val getTopAwaitFilmsUseCase: GetTopAwaitFilmsUseCase
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FilmListViewModel(
            type,
            getBestFilmsUseCase,
            getPopularFilmsUseCase,
            getTopAwaitFilmsUseCase) as T
    }

    @AssistedFactory
    interface Factory{
        fun create(@Assisted("type") type: Int): FilmListViewModelFactory
    }
}