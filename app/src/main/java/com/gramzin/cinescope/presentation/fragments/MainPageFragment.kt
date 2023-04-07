package com.gramzin.cinescope.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.gramzin.cinescope.R
import com.gramzin.cinescope.databinding.MainPageFragmentBinding
import com.gramzin.cinescope.presentation.fragments.adapter.MainPagerAdapter

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
                FilmListFragment.MainFilmCategories.TOP_POPULAR.ordinal -> tab.text = getString(R.string.top_popular)
                FilmListFragment.MainFilmCategories.TOP_BEST.ordinal -> tab.text = getString(R.string.top_best)
                else -> tab.text = getString(R.string.top_await)
            }
        }.attach()
        return binding.root
    }
}