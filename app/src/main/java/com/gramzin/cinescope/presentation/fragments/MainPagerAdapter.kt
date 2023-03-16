package com.gramzin.cinescope.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = FilmListFragment()

        val category = when(position) {
            0 -> FilmListFragment.MainFilmCategories.TOP_POPULAR.ordinal
            1 -> FilmListFragment.MainFilmCategories.TOP_BEST.ordinal
            else -> FilmListFragment.MainFilmCategories.TOP_AWAIT.ordinal
        }
        fragment.arguments = Bundle().apply {
            putInt(FilmListFragment.CATEGORY_KEY, category)
        }
        return fragment
    }
}