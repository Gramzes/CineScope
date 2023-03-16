package com.gramzin.cinescope.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gramzin.cinescope.databinding.CategoriesListFragmentBinding

class CategoriesListFragment : Fragment() {
    lateinit var binding: CategoriesListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoriesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}