package com.gramzin.cinescope.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gramzin.cinescope.databinding.CategoriesListFragmentBinding
import com.gramzin.cinescope.databinding.FilmListFragmentBinding

class FilmListFragment : Fragment() {
    lateinit var binding: FilmListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FilmListFragmentBinding.inflate(inflater, container, false)

        val categoryName = when (requireArguments().getInt(CATEGORY_KEY)) {
            MainFilmCategories.TOP_POPULAR.ordinal -> "Топ популярных"
            MainFilmCategories.TOP_BEST.ordinal -> "Топ лучших"
            else -> "Топ ожидаемых"
        }
        binding.categoryName.text = categoryName
        return binding.root
    }

    companion object{
        const val CATEGORY_KEY = "CATEGORY_KEY"
    }

    enum class MainFilmCategories {
        TOP_POPULAR,
        TOP_BEST,
        TOP_AWAIT
    }
}