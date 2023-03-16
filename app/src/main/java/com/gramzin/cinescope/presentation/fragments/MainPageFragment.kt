package com.gramzin.cinescope.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.gramzin.cinescope.databinding.CategoriesListFragmentBinding
import com.gramzin.cinescope.databinding.MainPageFragmentBinding

class MainPageFragment : Fragment() {
    lateinit var binding: MainPageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainPageFragmentBinding.inflate(inflater, container, false)

        val pagerAdapter = MainPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                FilmListFragment.MainFilmCategories.TOP_POPULAR.ordinal -> tab.text = "Топ популярных"
                FilmListFragment.MainFilmCategories.TOP_BEST.ordinal -> tab.text = "Топ лучших"
                else -> tab.text = "Топ ожидаемых"
            }
        }.attach()
        return binding.root
    }
}