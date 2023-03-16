package com.gramzin.cinescope.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gramzin.cinescope.databinding.CategoriesFragmentBinding

class CategoriesFragment : Fragment() {
    lateinit var binding: CategoriesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoriesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}